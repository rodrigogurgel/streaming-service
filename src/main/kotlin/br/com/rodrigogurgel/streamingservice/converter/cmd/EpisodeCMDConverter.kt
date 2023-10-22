package br.com.rodrigogurgel.streamingservice.converter.cmd

import br.com.rodrigogurgel.streamingservice.cmd.episode.CreateEpisodeCMD
import br.com.rodrigogurgel.streamingservice.domain.Episode
import java.time.OffsetDateTime

object EpisodeCMDConverter {
    fun toDomain(seasonId: Long, createEpisodeCMD: CreateEpisodeCMD): Episode {
        return with(createEpisodeCMD) {
            Episode(
                id = 0,
                seasonId = seasonId,
                title = title,
                link = null,
                metadata = emptyMap(),
                releaseDate = releaseDate,
                createdAt = OffsetDateTime.now(),
                updatedAt = OffsetDateTime.now()
            )
        }
    }
}