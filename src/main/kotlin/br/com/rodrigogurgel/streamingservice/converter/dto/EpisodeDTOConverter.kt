package br.com.rodrigogurgel.streamingservice.converter.dto

import br.com.rodrigogurgel.streamingservice.domain.Episode
import br.com.rodrigogurgel.streamingservice.dto.episode.EpisodeDTO

object EpisodeDTOConverter {
    fun from(episode: Episode): EpisodeDTO {
        return with(episode) {
            EpisodeDTO(
                id = id,
                seasonId = seasonId,
                title = title,
                metadata = metadata,
                link = link,
                releaseDate = releaseDate,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }
}