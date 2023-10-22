package br.com.rodrigogurgel.streamingservice.controller

import br.com.rodrigogurgel.streamingservice.cmd.season.CreateSeasonCMD
import br.com.rodrigogurgel.streamingservice.dto.season.SeasonDTO
import br.com.rodrigogurgel.streamingservice.service.SeasonService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/season")
@RestController
class SeasonController(private val seasonService: SeasonService) {
    @PostMapping("/streaming/{streamingId}")
    fun create(
        @PathVariable streamingId: Long,
        @Valid @RequestBody createSeasonCMD: CreateSeasonCMD,
    ): SeasonDTO {
        return seasonService.create(streamingId, createSeasonCMD)
    }
}