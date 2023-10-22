package br.com.rodrigogurgel.streamingservice.service.impl

import br.com.rodrigogurgel.streamingservice.cmd.streaming.CreateStreamingCMD
import br.com.rodrigogurgel.streamingservice.converter.cmd.StreamingCMDConverter
import br.com.rodrigogurgel.streamingservice.converter.dto.StreamingDTOConverter
import br.com.rodrigogurgel.streamingservice.dto.streaming.StreamingDTO
import br.com.rodrigogurgel.streamingservice.repository.StreamingRepository
import br.com.rodrigogurgel.streamingservice.service.StreamingService
import org.springframework.stereotype.Service

@Service
class StreamingServiceImpl(
    private val streamingRepository: StreamingRepository
): StreamingService {
    override fun create(createStreamingCMD: CreateStreamingCMD): StreamingDTO {
        val streamingDomain = StreamingCMDConverter.to(createStreamingCMD)
        return streamingRepository.insert(streamingDomain).let { StreamingDTOConverter.from(it) }
    }
}