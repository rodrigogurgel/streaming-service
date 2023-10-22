package br.com.rodrigogurgel.streamingservice.converter.dto

import br.com.rodrigogurgel.streamingservice.domain.Streaming
import br.com.rodrigogurgel.streamingservice.dto.streaming.StreamingDTO

object StreamingDTOConverter {
    fun from(from: Streaming): StreamingDTO {
        return with(from) {
            StreamingDTO(
                id= id,
                title = title,
                description = description,
                image = image,
                alternativeLocalTitles = alternativeLocalTitles,
                releaseDate = releaseDate,
                type = type,
                genres = genres,
                audioLocales = audioLocales,
                subtitleLocales = subtitleLocales,
                contentProvider = contentProvider,
            )
        }
    }
}