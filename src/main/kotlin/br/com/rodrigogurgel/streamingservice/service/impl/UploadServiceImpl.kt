package br.com.rodrigogurgel.streamingservice.service.impl

import br.com.rodrigogurgel.streamingservice.config.properties.BucketProperties
import br.com.rodrigogurgel.streamingservice.domain.UploadProcess
import br.com.rodrigogurgel.streamingservice.domain.UploadProcessStatusEnum
import br.com.rodrigogurgel.streamingservice.repository.EpisodeUploadRepository
import br.com.rodrigogurgel.streamingservice.repository.UploadProcessRepository
import br.com.rodrigogurgel.streamingservice.service.UploadService
import java.lang.Exception
import java.time.OffsetDateTime
import java.util.UUID
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Service
class UploadServiceImpl(
    private val s3Client: S3Client,
    private val bucketProperties: BucketProperties,
    private val uploadProcessRepository: UploadProcessRepository,
    private val episodeUploadRepository: EpisodeUploadRepository,
) : UploadService {

    @Async
    override fun uploadFile(
        uploadProcessId: UUID,
        key: String,
        bytes: ByteArray,
        contentType: String?,
        callbackSuccessHandler: () -> Unit,
    ) {
        try {
            uploadProcessRepository.updateStatus(uploadProcessId, UploadProcessStatusEnum.UPLOAD_STARTED)
            val putObjectRequest = PutObjectRequest
                .builder()
                .bucket(bucketProperties.names.videos)
                .key(key)
                .contentType(contentType)
                .build()

            val requestBody = RequestBody.fromBytes(bytes)

            s3Client.putObject(putObjectRequest, requestBody)
            uploadProcessRepository.updateStatus(uploadProcessId, UploadProcessStatusEnum.UPLOAD_COMPLETED)
            callbackSuccessHandler()
        } catch (e: Exception) {
            uploadProcessRepository.updateStatus(uploadProcessId, UploadProcessStatusEnum.UPLOAD_FAILED)
            throw e
        }
    }

    @Transactional
    override fun createUploadEpisodeProcess(episodeId: Long, uploadProcessId: UUID): UploadProcess {
        val uploadProcess = UploadProcess(
            id = uploadProcessId,
            status = UploadProcessStatusEnum.UPLOAD_PENDING,
            createdAt = OffsetDateTime.now(),
            updatedAt = OffsetDateTime.now()
        )
        val uploadProcessDb = uploadProcessRepository.insert(uploadProcess)
        episodeUploadRepository.insert(episodeId, uploadProcessId)

        return uploadProcessDb
    }

    override fun updateStatus(uploadProcessId: UUID, status: UploadProcessStatusEnum) {
        uploadProcessRepository.updateStatus(uploadProcessId, status)
    }
}