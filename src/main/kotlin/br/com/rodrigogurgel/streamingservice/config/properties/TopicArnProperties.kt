package br.com.rodrigogurgel.streamingservice.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@ConfigurationProperties(prefix = "streaming-service.topics.arns")
@ConfigurationPropertiesScan
data class TopicArnProperties(
    val episodeUploaded: String
)