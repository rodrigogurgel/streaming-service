package br.com.rodrigogurgel.streamingservice.config.properties

import java.security.MessageDigest
import java.util.Base64
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@ConfigurationProperties(prefix = "hash")
@ConfigurationPropertiesScan
data class HashProperties (
    val secret: String
) {
    fun hash(value: String): String {
        val md = MessageDigest.getInstance("MD5")
        val binaryHash = md.digest(value.toByteArray())
        val base64Value = String(Base64.getEncoder().encode(binaryHash))
        return base64Value.replace("=".toRegex(), "")
            .replace("\\+".toRegex(), "-")
            .replace("/".toRegex(), "_")
    }
}