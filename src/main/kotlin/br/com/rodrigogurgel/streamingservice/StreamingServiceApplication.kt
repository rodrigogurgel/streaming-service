package br.com.rodrigogurgel.streamingservice

import br.com.rodrigogurgel.streamingservice.config.properties.BucketProperties
import br.com.rodrigogurgel.streamingservice.config.properties.TopicArnProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
@EnableConfigurationProperties(value = [BucketProperties::class, TopicArnProperties::class])
class StreamingServiceApplication

fun main(args: Array<String>) {
	runApplication<StreamingServiceApplication>(*args)
}
