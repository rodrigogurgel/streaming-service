package br.com.rodrigogurgel.streamingservice.service

import java.util.UUID

interface EpisodeUploadService {
    fun createUploadEpisode(episodeId: Long, uploadProcessId: UUID)
}