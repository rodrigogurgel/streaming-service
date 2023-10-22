package br.com.rodrigogurgel.streamingservice.cmd.notification

data class EpisodeUploadedNotificationCMD(
    val key: String,
    val episodeId: Long
)
