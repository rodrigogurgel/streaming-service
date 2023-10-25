package br.com.rodrigogurgel.streamingservice.domain

data class EpisodeMetadata(
    val filePath: String?,
    val qualities: List<String>,
    val subtitles: List<String>? = null,
)
