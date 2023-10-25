package br.com.rodrigogurgel.streamingservice.controller

import br.com.rodrigogurgel.streamingservice.converter.cmd.UploadProcessUpdateStatusCMD
import br.com.rodrigogurgel.streamingservice.domain.SubtitleEnum
import br.com.rodrigogurgel.streamingservice.dto.uploadprocess.UploadProcessDTO
import br.com.rodrigogurgel.streamingservice.service.UploadProcessService
import java.util.UUID
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/upload-process")
class UploadProcessController(
    private val uploadProcessService: UploadProcessService,
) {
    @GetMapping("/{uploadProcessId}")
    fun getById(@PathVariable uploadProcessId: UUID): UploadProcessDTO {
        return uploadProcessService.fetchById(uploadProcessId)
    }

    @PutMapping(
        "/episode/{episodeId}/upload",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun uploadVideo(
        @PathVariable episodeId: Long,
        @RequestPart file: MultipartFile,
    ): UploadProcessDTO {
        return uploadProcessService.uploadVideo(episodeId, file)
    }

    @PutMapping("/{uploadProcessId}/status")
    fun updateStatus(
        @PathVariable uploadProcessId: UUID,
        @RequestBody uploadProcessUpdateStatusCMD: UploadProcessUpdateStatusCMD,
    ) {
        uploadProcessService.updateStatus(uploadProcessId, uploadProcessUpdateStatusCMD.status)
    }

    @PutMapping(
        "/episode/{episodeId}/{subtitleEnum}/subtitles",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun uploadSubtitle(
        @PathVariable episodeId: Long,
        @PathVariable subtitleEnum: SubtitleEnum,
        @RequestPart file: MultipartFile,
    ): UploadProcessDTO {
        return uploadProcessService.uploadSubtitle(episodeId, subtitleEnum, file)
    }
}