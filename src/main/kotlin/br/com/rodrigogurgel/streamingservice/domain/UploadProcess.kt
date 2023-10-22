package br.com.rodrigogurgel.streamingservice.domain

import java.time.OffsetDateTime
import java.util.UUID

data class UploadProcess(
    val id: UUID,
    val status: UploadProcessStatusEnum,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
)
