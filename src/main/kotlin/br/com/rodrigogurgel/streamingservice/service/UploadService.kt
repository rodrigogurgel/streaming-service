package br.com.rodrigogurgel.streamingservice.service

import br.com.rodrigogurgel.streamingservice.domain.UploadProcess
import br.com.rodrigogurgel.streamingservice.domain.UploadProcessStatusEnum
import java.util.UUID
import org.springframework.web.multipart.MultipartFile

interface UploadService {
    fun uploadFile(
        uploadProcessId: UUID,
        key: String,
        bytes: ByteArray,
        contentType: String?,
        callbackSuccessHandler: () -> Unit,
    )

    fun createUploadEpisodeProcess(episodeId: Long, uploadProcessId: UUID): UploadProcess

    fun updateStatus(uploadProcessId: UUID, status: UploadProcessStatusEnum)
}