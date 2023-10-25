package br.com.rodrigogurgel.streamingservice.repository

import br.com.rodrigogurgel.streamingservice.domain.UploadProcess
import br.com.rodrigogurgel.streamingservice.domain.UploadProcessStatusEnum
import java.util.UUID

interface UploadProcessRepository {
    fun insert(uploadProcess: UploadProcess): UploadProcess
    fun updateStatus(uploadProcessId: UUID, status: UploadProcessStatusEnum)

    fun selectById(uploadProcessId: UUID): UploadProcess
}