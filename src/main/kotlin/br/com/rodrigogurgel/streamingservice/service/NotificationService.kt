package br.com.rodrigogurgel.streamingservice.service

import br.com.rodrigogurgel.streamingservice.cmd.notification.EpisodeUploadedNotificationCMD

interface NotificationService {
    fun notifyEpisodeUploaded(videoUploadedNotificationCMD: EpisodeUploadedNotificationCMD)
}