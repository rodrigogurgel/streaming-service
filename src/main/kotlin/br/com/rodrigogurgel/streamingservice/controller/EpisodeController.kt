package br.com.rodrigogurgel.streamingservice.controller

import br.com.rodrigogurgel.streamingservice.cmd.episode.CreateEpisodeCMD
import br.com.rodrigogurgel.streamingservice.cmd.episode.PathEpisodeMetadataCMD
import br.com.rodrigogurgel.streamingservice.dto.episode.EpisodeDTO
import br.com.rodrigogurgel.streamingservice.service.EpisodeService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/episode")
class EpisodeController(
    private val episodeService: EpisodeService,
) {
    @PostMapping("/streaming/{streamingId}/season/{seasonId}")
    fun create(
        @PathVariable seasonId: Long,
        @PathVariable streamingId: Long,
        @RequestBody @Valid createEpisodeCMD: CreateEpisodeCMD,
    ): EpisodeDTO {
        return episodeService.create(streamingId, seasonId, createEpisodeCMD)
    }

    @GetMapping("/{episodeId}")
    fun getEpisode(@PathVariable episodeId: Long): EpisodeDTO {
        return episodeService.fetchById(episodeId)
    }

    @PatchMapping("/{episodeId}/metadata")
    fun updateEpisodeMetadata(@PathVariable episodeId: Long, @RequestBody pathEpisodeMetadataCMD: PathEpisodeMetadataCMD) {
        episodeService.pathEpisodeMetadata(episodeId, pathEpisodeMetadataCMD)
    }
}