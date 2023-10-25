package br.com.rodrigogurgel.streamingservice.domain

import java.time.OffsetDateTime

data class Episode(
    val id: Long,
    val seasonId: Long,
    val title: String,
    val metadata: EpisodeMetadata,
    val releaseDate: OffsetDateTime?,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
)