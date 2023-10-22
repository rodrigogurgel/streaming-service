package br.com.rodrigogurgel.streamingservice.repository

import java.util.UUID

interface EpisodeUploadRepository {
    fun insert(episodeId: Long, uploadProcessId: UUID)
}