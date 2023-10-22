package br.com.rodrigogurgel.streamingservice.repository.mapper

import br.com.rodrigogurgel.streamingservice.domain.Streaming
import br.com.rodrigogurgel.streamingservice.domain.StreamingTypeEnum
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.sql.ResultSet
import java.time.OffsetDateTime
import java.time.ZoneOffset
import org.springframework.jdbc.core.RowMapper

class StreamingRowMapper : RowMapper<Streaming> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Streaming {
        rs.getArray("genres")
        return Streaming(
            id = rs.getLong("id"),
            title = rs.getString("title"),
            description = rs.getString("description"),
            image = rs.getString("image"),
            alternativeLocalTitles = jacksonObjectMapper()
                .readValue(
                    rs.getString("alternative_local_titles"),
                    object : TypeReference<Map<String, String>>() {}
                ),
            releaseDate = OffsetDateTime.of(
                rs.getTimestamp("release_date").toLocalDateTime(),
                ZoneOffset.ofHours(-3)
            ),
            type = StreamingTypeEnum.valueOf(rs.getString("type")),
            genres = (rs.getArray("genres").array as Array<String>).toList(),
            audioLocales = (rs.getArray("audio_locales").array as Array<String>).toList(),
            subtitleLocales = (rs.getArray("subtitle_locales").array as Array<String>).toList(),
            contentProvider = rs.getString("content_provider"),
            createdAt = OffsetDateTime.of(
                rs.getTimestamp("created_at").toLocalDateTime(),
                ZoneOffset.ofHours(-3)
            ),
            updatedAt = OffsetDateTime.of(
                rs.getTimestamp("updated_at").toLocalDateTime(),
                ZoneOffset.ofHours(-3)
            )
        )
    }
}