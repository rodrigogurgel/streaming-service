package br.com.rodrigogurgel.streamingservice.service.impl

import br.com.rodrigogurgel.streamingservice.config.properties.BucketProperties
import br.com.rodrigogurgel.streamingservice.service.UploadService
import java.util.UUID
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest

@Service
class UploadServiceImpl(
    private val s3Client: S3Client,
    private val bucketProperties: BucketProperties,
) : UploadService {

    companion object {
        private val logger = LoggerFactory.getLogger(UploadServiceImpl::class.java)
    }

    @Async
    override fun uploadFile(
        uploadProcessId: UUID,
        key: String,
        bytes: ByteArray,
        contentType: String?,
        callbackSuccess: () -> Unit,
        callbackError: (error: RuntimeException) -> Unit,
    ) {
        try {
            val putObjectRequest = PutObjectRequest
                .builder()
                .bucket(bucketProperties.names.videos)
                .key(key)
                .contentType(contentType)
                .build()

            val requestBody = RequestBody.fromBytes(bytes)

            s3Client.putObject(putObjectRequest, requestBody)
            callbackSuccess()
        } catch (e: RuntimeException) {
            callbackError(e)
        }
    }
}