package br.com.rodrigogurgel.streamingservice.dto.episode

import java.time.OffsetDateTime

data class EpisodeDTO(
    val id: Long,
    val seasonId: Long,
    val title: String,
    val metadata: Map<String, String>,
    val link: String?,
    val releaseDate: OffsetDateTime?,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
)
