package br.com.rodrigogurgel.streamingservice.converter.dto

import br.com.rodrigogurgel.streamingservice.domain.Season
import br.com.rodrigogurgel.streamingservice.dto.season.SeasonDTO

object SeasonDTOConverter {
    fun from(season: Season): SeasonDTO {
        return with(season) {
            SeasonDTO(
                id = id,
                streamingId = streamingId,
                description = description,
                title = title,
                totalEpisodes = totalEpisodes,
                releaseDate = releaseDate
            )
        }
    }
}