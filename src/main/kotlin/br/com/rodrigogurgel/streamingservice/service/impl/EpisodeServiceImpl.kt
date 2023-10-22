package br.com.rodrigogurgel.streamingservice.service.impl

import br.com.rodrigogurgel.streamingservice.cmd.episode.CreateEpisodeCMD
import br.com.rodrigogurgel.streamingservice.cmd.notification.EpisodeUploadedNotificationCMD
import br.com.rodrigogurgel.streamingservice.converter.cmd.EpisodeCMDConverter
import br.com.rodrigogurgel.streamingservice.converter.dto.EpisodeDTOConverter
import br.com.rodrigogurgel.streamingservice.converter.dto.UploadProcessDTOConverter
import br.com.rodrigogurgel.streamingservice.domain.UploadProcessStatusEnum
import br.com.rodrigogurgel.streamingservice.dto.episode.EpisodeDTO
import br.com.rodrigogurgel.streamingservice.dto.uploadprocess.UploadProcessDTO
import br.com.rodrigogurgel.streamingservice.exception.SeasonNotFoundException
import br.com.rodrigogurgel.streamingservice.repository.EpisodeRepository
import br.com.rodrigogurgel.streamingservice.repository.SeasonRepository
import br.com.rodrigogurgel.streamingservice.service.EpisodeService
import br.com.rodrigogurgel.streamingservice.service.NotificationService
import br.com.rodrigogurgel.streamingservice.service.UploadService
import java.util.UUID
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class EpisodeServiceImpl(
    private val episodeRepository: EpisodeRepository,
    private val seasonRepository: SeasonRepository,
    private val uploadService: UploadService,
    private val notificationService: NotificationService,
) : EpisodeService {
    override fun create(streamingId: Long, seasonId: Long, createEpisodeCMD: CreateEpisodeCMD): EpisodeDTO {
        seasonRepository.selectByIdAndStreamingId(seasonId, streamingId) ?: throw SeasonNotFoundException(
            seasonId,
            streamingId
        )

        val episode = EpisodeCMDConverter.toDomain(seasonId, createEpisodeCMD)
        val episodeDb = episodeRepository.insert(episode)
        return EpisodeDTOConverter.from(episodeDb)
    }

    override fun uploadVideo(episodeId: Long, file: MultipartFile): UploadProcessDTO {
        val key = "$episodeId/original/file"

        val uploadProcessId = UUID.randomUUID()
        val uploadProcess = uploadService.createUploadEpisodeProcess(episodeId, uploadProcessId)
        uploadService.uploadFile(uploadProcessId, key, file.bytes, file.contentType) {
            val episodeUploadedNotificationCMD = EpisodeUploadedNotificationCMD(
                key = key, episodeId = episodeId
            )
            notificationService.notifyEpisodeUploaded(episodeUploadedNotificationCMD)
            uploadService.updateStatus(uploadProcessId, UploadProcessStatusEnum.CONVERSION_PENDING)
        }

        return UploadProcessDTOConverter.from(uploadProcess)
    }
}