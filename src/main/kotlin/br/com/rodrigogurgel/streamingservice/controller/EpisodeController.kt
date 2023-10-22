package br.com.rodrigogurgel.streamingservice.controller

import br.com.rodrigogurgel.streamingservice.cmd.episode.CreateEpisodeCMD
import br.com.rodrigogurgel.streamingservice.dto.episode.EpisodeDTO
import br.com.rodrigogurgel.streamingservice.dto.uploadprocess.UploadProcessDTO
import br.com.rodrigogurgel.streamingservice.service.EpisodeService
import jakarta.validation.Valid
import java.util.UUID
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

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

    @PutMapping(
        "/{episodeId}/upload",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun uploadFile(
        @PathVariable episodeId: Long,
        @RequestPart file: MultipartFile,
    ): UploadProcessDTO {
        return episodeService.uploadVideo(episodeId, file)
    }
}