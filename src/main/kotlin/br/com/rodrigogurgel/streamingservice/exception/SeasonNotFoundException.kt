package br.com.rodrigogurgel.streamingservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class SeasonNotFoundException private constructor(message: String) : RuntimeException(message) {
    constructor(
        seasonId: Long,
        streamingId: Long,
    ) : this("Can't be found season with id: $seasonId and streaming id: $streamingId")
}