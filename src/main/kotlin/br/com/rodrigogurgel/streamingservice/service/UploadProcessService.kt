package br.com.rodrigogurgel.streamingservice.service

import br.com.rodrigogurgel.streamingservice.domain.SubtitleEnum
import br.com.rodrigogurgel.streamingservice.domain.UploadProcessStatusEnum
import br.com.rodrigogurgel.streamingservice.dto.uploadprocess.UploadProcessDTO
import java.util.UUID
import org.springframework.web.multipart.MultipartFile

interface UploadProcessService {
    fun uploadVideo(episodeId: Long, file: MultipartFile): UploadProcessDTO

    fun createUploadProcess(episodeId: Long, uploadProcessId: UUID): UploadProcessDTO

    fun updateStatus(uploadProcessId: UUID, status: UploadProcessStatusEnum)

    fun fetchById(uploadProcessId: UUID): UploadProcessDTO

    fun uploadSubtitle(episodeId: Long, subtitleEnum: SubtitleEnum, file: MultipartFile): UploadProcessDTO
}