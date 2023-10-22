package br.com.rodrigogurgel.streamingservice.domain

import java.time.OffsetDateTime

data class Season(
    val id: Long,
    val streamingId: Long,
    val title: String,
    val description: String,
    val totalEpisodes: Long?,
    val releaseDate: OffsetDateTime?,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
)
