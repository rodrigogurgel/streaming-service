package br.com.rodrigogurgel.streamingservice.dto.uploadprocess

import br.com.rodrigogurgel.streamingservice.domain.UploadProcessStatusEnum
import java.time.OffsetDateTime
import java.util.UUID

data class UploadProcessDTO(
    val id: UUID,
    val status: UploadProcessStatusEnum,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
)