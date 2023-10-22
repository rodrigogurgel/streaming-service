package br.com.rodrigogurgel.streamingservice.converter.cmd

import br.com.rodrigogurgel.streamingservice.cmd.season.CreateSeasonCMD
import br.com.rodrigogurgel.streamingservice.domain.Season
import java.time.OffsetDateTime

object SeasonCMDConverter {
    fun to(streamingId: Long, createSeasonCMD: CreateSeasonCMD): Season {
        return with(createSeasonCMD) {
            Season(
                id = 0,
                streamingId = streamingId,
                title = title,
                description = description,
                totalEpisodes = totalEpisodes,
                releaseDate = releaseDate,
                createdAt = OffsetDateTime.now(),
                updatedAt = OffsetDateTime.now(),
            )
        }
    }
}