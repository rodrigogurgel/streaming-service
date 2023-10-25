package br.com.rodrigogurgel.streamingservice.converter.cmd

import br.com.rodrigogurgel.streamingservice.cmd.episode.CreateEpisodeCMD
import br.com.rodrigogurgel.streamingservice.domain.Episode
import br.com.rodrigogurgel.streamingservice.domain.EpisodeMetadata
import java.time.OffsetDateTime

object EpisodeCMDConverter {
    fun toDomain(seasonId: Long, createEpisodeCMD: CreateEpisodeCMD): Episode {
        return with(createEpisodeCMD) {
            Episode(
                id = 0,
                seasonId = seasonId,
                title = title,
                metadata = EpisodeMetadata(qualities = emptyList(), filePath = null, subtitles = emptyList()),
                releaseDate = releaseDate,
                createdAt = OffsetDateTime.now(),
                updatedAt = OffsetDateTime.now()
            )
        }
    }
}