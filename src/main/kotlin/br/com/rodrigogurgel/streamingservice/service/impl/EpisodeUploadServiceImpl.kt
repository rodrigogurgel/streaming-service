package br.com.rodrigogurgel.streamingservice.service.impl

import br.com.rodrigogurgel.streamingservice.repository.EpisodeUploadRepository
import br.com.rodrigogurgel.streamingservice.service.EpisodeUploadService
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class EpisodeUploadServiceImpl(
    private val episodeUploadRepository: EpisodeUploadRepository
): EpisodeUploadService {
    override fun createUploadEpisode(episodeId: Long, uploadProcessId: UUID) {
        episodeUploadRepository.insert(episodeId, uploadProcessId)
    }
}