package br.com.rodrigogurgel.streamingservice.service.impl

import br.com.rodrigogurgel.streamingservice.cmd.notification.EpisodeUploadedNotificationCMD
import br.com.rodrigogurgel.streamingservice.config.properties.HashProperties
import br.com.rodrigogurgel.streamingservice.config.properties.VodProperties
import br.com.rodrigogurgel.streamingservice.converter.dto.UploadProcessDTOConverter
import br.com.rodrigogurgel.streamingservice.domain.SubtitleEnum
import br.com.rodrigogurgel.streamingservice.domain.UploadProcess
import br.com.rodrigogurgel.streamingservice.domain.UploadProcessStatusEnum
import br.com.rodrigogurgel.streamingservice.dto.uploadprocess.UploadProcessDTO
import br.com.rodrigogurgel.streamingservice.repository.EpisodeRepository
import br.com.rodrigogurgel.streamingservice.repository.UploadProcessRepository
import br.com.rodrigogurgel.streamingservice.service.EpisodeUploadService
import br.com.rodrigogurgel.streamingservice.service.NotificationService
import br.com.rodrigogurgel.streamingservice.service.UploadProcessService
import br.com.rodrigogurgel.streamingservice.service.UploadService
import java.time.OffsetDateTime
import java.util.UUID
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UploadProcessServiceImpl(
    private val notificationService: NotificationService,
    private val uploadService: UploadService,
    private val uploadProcessRepository: UploadProcessRepository,
    private val episodeUploadService: EpisodeUploadService,
    private val episodeRepository: EpisodeRepository,
    private val vodProperties: VodProperties,
    private val hashProperties: HashProperties,
) : UploadProcessService {

    override fun uploadVideo(episodeId: Long, file: MultipartFile): UploadProcessDTO {
        val key = "$episodeId/original/file"

        val uploadProcessId = UUID.randomUUID()
        val uploadProcess = createUploadProcess(episodeId, uploadProcessId)

        episodeUploadService.createUploadEpisode(episodeId, uploadProcessId)

        updateStatus(uploadProcessId, UploadProcessStatusEnum.UPLOAD_STARTED)

        uploadService.uploadFile(
            uploadProcessId,
            key,
            file.bytes,
            file.contentType, {
                updateStatus(uploadProcessId, UploadProcessStatusEnum.UPLOAD_COMPLETED)
                val episodeUploadedNotificationCMD = EpisodeUploadedNotificationCMD(
                    key = key, episodeId = episodeId, uploadProcessId = uploadProcessId
                )
                try {
                    notificationService.notifyEpisodeUploaded(episodeUploadedNotificationCMD)
                    updateStatus(uploadProcessId, UploadProcessStatusEnum.CONVERSION_PENDING)
                } catch (e: RuntimeException) {
                    updateStatus(uploadProcessId, UploadProcessStatusEnum.CONVERSION_NOTIFICATION_FAILED)
                }
            }, {
                updateStatus(uploadProcessId, UploadProcessStatusEnum.UPLOAD_FAILED)
            })

        return uploadProcess
    }

    override fun createUploadProcess(episodeId: Long, uploadProcessId: UUID): UploadProcessDTO {
        val uploadProcess = UploadProcess(
            id = uploadProcessId,
            status = UploadProcessStatusEnum.UPLOAD_PENDING,
            createdAt = OffsetDateTime.now(),
            updatedAt = OffsetDateTime.now()
        )
        val uploadProcessDb = uploadProcessRepository.insert(uploadProcess)

        return UploadProcessDTOConverter.from(uploadProcessDb)
    }

    override fun updateStatus(uploadProcessId: UUID, status: UploadProcessStatusEnum) {
        uploadProcessRepository.updateStatus(uploadProcessId, status)
    }

    override fun fetchById(uploadProcessId: UUID): UploadProcessDTO {
        val uploadProcessDb = uploadProcessRepository.selectById(uploadProcessId)
        return UploadProcessDTOConverter.from(uploadProcessDb)
    }

    override fun uploadSubtitle(episodeId: Long, subtitleEnum: SubtitleEnum, file: MultipartFile): UploadProcessDTO {
        val episodeDb = episodeRepository.selectById(episodeId)

        val uploadProcessId = UUID.randomUUID()
        val uploadProcess = createUploadProcess(episodeId, uploadProcessId)

        episodeUploadService.createUploadEpisode(episodeId, uploadProcessId)

        updateStatus(uploadProcessId, UploadProcessStatusEnum.UPLOAD_STARTED)

        val id = hashProperties.hash("${hashProperties.secret}${episodeId}")

        val key = "vod/$id/${episodeDb.metadata.filePath}/subtitles_${subtitleEnum.`iso639-2`}.srt"

        uploadService.uploadFile(
            uploadProcessId,
            key,
            file.bytes,
            file.contentType, {
                val metadata = episodeDb.metadata
                val subtitles = episodeDb.metadata.subtitles
                episodeRepository.update(episodeDb.copy(metadata = metadata.copy(subtitles = subtitles.orEmpty() + subtitleEnum.`iso639-2`)))
                updateStatus(uploadProcessId, UploadProcessStatusEnum.UPLOAD_COMPLETED)
            }, {
                updateStatus(uploadProcessId, UploadProcessStatusEnum.UPLOAD_FAILED)
            })

        return uploadProcess
    }
}