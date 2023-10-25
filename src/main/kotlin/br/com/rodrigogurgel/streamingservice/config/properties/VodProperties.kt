package br.com.rodrigogurgel.streamingservice.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@ConfigurationProperties(prefix = "vod")
@ConfigurationPropertiesScan
data class VodProperties(
    val ip: String?,
    val baseUrl: String,
    val suffix: String
)