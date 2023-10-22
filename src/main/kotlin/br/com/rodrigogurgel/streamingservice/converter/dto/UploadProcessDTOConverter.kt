package br.com.rodrigogurgel.streamingservice.converter.dto

import br.com.rodrigogurgel.streamingservice.domain.UploadProcess
import br.com.rodrigogurgel.streamingservice.dto.uploadprocess.UploadProcessDTO

object UploadProcessDTOConverter {
    fun from(uploadProcess: UploadProcess): UploadProcessDTO {
        return with(uploadProcess) {
            UploadProcessDTO(
                id = id,
                status = status,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }
}