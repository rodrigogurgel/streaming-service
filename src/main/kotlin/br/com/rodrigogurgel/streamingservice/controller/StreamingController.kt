package br.com.rodrigogurgel.streamingservice.controller

import br.com.rodrigogurgel.streamingservice.cmd.streaming.CreateStreamingCMD
import br.com.rodrigogurgel.streamingservice.dto.streaming.StreamingDTO
import br.com.rodrigogurgel.streamingservice.service.StreamingService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/streaming")
class StreamingController(
    private val streamingService: StreamingService,
) {
    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
        @RequestBody @Valid createStreamingCMD: CreateStreamingCMD,
    ): StreamingDTO {
        return streamingService.create(createStreamingCMD)
    }
}