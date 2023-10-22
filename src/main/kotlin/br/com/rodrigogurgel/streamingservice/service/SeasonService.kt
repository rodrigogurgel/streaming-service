package br.com.rodrigogurgel.streamingservice.service

import br.com.rodrigogurgel.streamingservice.cmd.season.CreateSeasonCMD
import br.com.rodrigogurgel.streamingservice.dto.season.SeasonDTO

interface SeasonService {
    fun create(streamingId: Long, createSeasonCMD: CreateSeasonCMD): SeasonDTO
}