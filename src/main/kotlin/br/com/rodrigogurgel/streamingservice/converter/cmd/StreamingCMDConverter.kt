package br.com.rodrigogurgel.streamingservice.converter.cmd

import br.com.rodrigogurgel.streamingservice.cmd.streaming.CreateStreamingCMD
import br.com.rodrigogurgel.streamingservice.domain.Streaming
import java.time.OffsetDateTime

object StreamingCMDConverter {
    fun to(from: CreateStreamingCMD): Streaming {
        return with(from) {
            Streaming(
                id = 0,
                title = title,
                description = description,
                image = null,
                alternativeLocalTitles = alternativeLocalTitles,
                releaseDate = releaseDate,
                type = type,
                genres = genres,
                audioLocales = audioLocales,
                subtitleLocales = subtitleLocales,
                contentProvider = contentProvider,
                createdAt = OffsetDateTime.now(),
                updatedAt = OffsetDateTime.now(),
            )
        }
    }
}