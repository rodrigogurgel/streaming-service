package br.com.rodrigogurgel.streamingservice.repository

import br.com.rodrigogurgel.streamingservice.domain.Streaming

interface StreamingRepository {
    fun insert(streaming: Streaming): Streaming
    fun selectById(streamingId: Long): Streaming?
}