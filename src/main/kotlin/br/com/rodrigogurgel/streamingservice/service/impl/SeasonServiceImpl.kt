package br.com.rodrigogurgel.streamingservice.service.impl

import br.com.rodrigogurgel.streamingservice.cmd.season.CreateSeasonCMD
import br.com.rodrigogurgel.streamingservice.converter.cmd.SeasonCMDConverter
import br.com.rodrigogurgel.streamingservice.converter.dto.SeasonDTOConverter
import br.com.rodrigogurgel.streamingservice.dto.season.SeasonDTO
import br.com.rodrigogurgel.streamingservice.exception.StreamingNotFoundException
import br.com.rodrigogurgel.streamingservice.repository.SeasonRepository
import br.com.rodrigogurgel.streamingservice.repository.StreamingRepository
import br.com.rodrigogurgel.streamingservice.service.SeasonService
import org.springframework.stereotype.Service

@Service
class SeasonServiceImpl(
    private val streamingRepository: StreamingRepository,
    private val seasonRepository: SeasonRepository,
) : SeasonService {
    override fun create(streamingId: Long, createSeasonCMD: CreateSeasonCMD): SeasonDTO {
        val seasonDomain = SeasonCMDConverter.to(streamingId, createSeasonCMD)
        streamingRepository.selectById(streamingId) ?: throw StreamingNotFoundException(streamingId)

        val seasonDb = seasonRepository.insert(seasonDomain)
        return SeasonDTOConverter.from(seasonDb)
    }
}