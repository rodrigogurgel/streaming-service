package br.com.rodrigogurgel.streamingservice.domain

import java.time.OffsetDateTime

data class Streaming(
    val id: Long,
    val title: String,
    val description: String,
    val image: String?,
    val alternativeLocalTitles: Map<String, String>,
    val releaseDate: OffsetDateTime?,
    val type: StreamingTypeEnum,
    val genres: List<String>,
    val audioLocales: List<String>,
    val subtitleLocales: List<String>,
    val contentProvider: String,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
)
