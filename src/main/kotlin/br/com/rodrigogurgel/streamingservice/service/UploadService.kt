package br.com.rodrigogurgel.streamingservice.service

import java.util.UUID

interface UploadService {
    fun uploadFile(
        uploadProcessId: UUID,
        key: String,
        bytes: ByteArray,
        contentType: String?,
        callbackSuccess: () -> Unit,
        callbackError: (error: RuntimeException) -> Unit,
    )
}