package br.com.rodrigogurgel.streamingservice.cmd.notification

import java.util.UUID

data class EpisodeUploadedNotificationCMD(
    val key: String,
    val uploadProcessId: UUID,
    val episodeId: Long
)
