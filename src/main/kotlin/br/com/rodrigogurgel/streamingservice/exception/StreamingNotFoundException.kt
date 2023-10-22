package br.com.rodrigogurgel.streamingservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class StreamingNotFoundException private constructor(message: String) : RuntimeException(message) {
    constructor(streamingId: Long) : this("Can't be found streaming with id: $streamingId")
}
