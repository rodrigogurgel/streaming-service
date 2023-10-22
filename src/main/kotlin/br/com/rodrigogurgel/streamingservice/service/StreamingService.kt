package br.com.rodrigogurgel.streamingservice.service

import br.com.rodrigogurgel.streamingservice.cmd.streaming.CreateStreamingCMD
import br.com.rodrigogurgel.streamingservice.dto.streaming.StreamingDTO

interface StreamingService {
    fun create(createStreamingCMD: CreateStreamingCMD): StreamingDTO
}