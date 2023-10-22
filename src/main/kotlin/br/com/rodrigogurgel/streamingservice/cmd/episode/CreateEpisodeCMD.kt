package br.com.rodrigogurgel.streamingservice.cmd.episode

import java.time.OffsetDateTime

data class CreateEpisodeCMD(
    val title: String,
    val releaseDate: OffsetDateTime?
)
