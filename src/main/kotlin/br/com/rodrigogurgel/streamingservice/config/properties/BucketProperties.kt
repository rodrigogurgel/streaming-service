package br.com.rodrigogurgel.streamingservice.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@ConfigurationProperties(prefix = "streaming-service.buckets")
@ConfigurationPropertiesScan
class BucketProperties(
    val names: NamesProperty,
) {
    class NamesProperty(
        val videos: String,
    )
}
