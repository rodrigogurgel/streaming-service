package br.com.rodrigogurgel.streamingservice.repository.mapper

import br.com.rodrigogurgel.streamingservice.domain.Episode
import br.com.rodrigogurgel.streamingservice.domain.EpisodeMetadata
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.sql.ResultSet
import java.time.OffsetDateTime
import java.time.ZoneOffset
import org.springframework.jdbc.core.RowMapper

class EpisodeRowMapper : RowMapper<Episode> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Episode {
        return Episode(
            id = rs.getLong("id"),
            seasonId = rs.getLong("season_id"),
            title = rs.getString("title"),
            metadata = jacksonObjectMapper()
                .readValue(
                    rs.getString("metadata"),
                    EpisodeMetadata::class.java
                ),
            releaseDate = rs.getTimestamp("release_date")?.let {
                OffsetDateTime.of(
                    it.toLocalDateTime(),
                    ZoneOffset.ofHours(-3)
                )
            },
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
