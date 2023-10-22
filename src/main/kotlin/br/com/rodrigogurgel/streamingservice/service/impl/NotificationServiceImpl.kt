package br.com.rodrigogurgel.streamingservice.service.impl

import br.com.rodrigogurgel.streamingservice.cmd.notification.EpisodeUploadedNotificationCMD
import br.com.rodrigogurgel.streamingservice.config.properties.TopicArnProperties
import br.com.rodrigogurgel.streamingservice.service.NotificationService
import io.awspring.cloud.sns.core.SnsTemplate
import org.springframework.stereotype.Service

@Service
class NotificationServiceImpl(
    private val snsTemplate: SnsTemplate,
    private val topicArnProperties: TopicArnProperties
): NotificationService {
    override fun notifyEpisodeUploaded(videoUploadedNotificationCMD: EpisodeUploadedNotificationCMD) {
        snsTemplate.convertAndSend(topicArnProperties.episodeUploaded, videoUploadedNotificationCMD)
    }
}