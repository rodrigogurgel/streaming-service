package br.com.rodrigogurgel.streamingservice.dto.episode

import br.com.rodrigogurgel.streamingservice.domain.EpisodeMetadata
import java.time.OffsetDateTime

data class EpisodeDTO(
    val id: Long,
    val seasonId: Long,
    val title: String,
    val metadata: EpisodeMetadata,
    val link: String?,
    val releaseDate: OffsetDateTime?,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
)
