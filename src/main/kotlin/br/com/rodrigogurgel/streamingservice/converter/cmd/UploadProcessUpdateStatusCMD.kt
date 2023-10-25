package br.com.rodrigogurgel.streamingservice.converter.cmd

import br.com.rodrigogurgel.streamingservice.domain.UploadProcessStatusEnum

data class UploadProcessUpdateStatusCMD(
    val status: UploadProcessStatusEnum
)
