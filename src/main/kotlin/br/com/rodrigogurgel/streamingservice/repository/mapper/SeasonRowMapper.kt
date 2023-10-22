package br.com.rodrigogurgel.streamingservice.repository.mapper

import br.com.rodrigogurgel.streamingservice.domain.Season
import java.sql.ResultSet
import java.time.OffsetDateTime
import java.time.ZoneOffset
import org.springframework.jdbc.core.RowMapper

class SeasonRowMapper : RowMapper<Season> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Season {
        return Season(
            id = rs.getLong("id"),
            streamingId = rs.getLong("streaming_id"),
            title = rs.getString("title"),
            description = rs.getString("description"),
            totalEpisodes = rs.getLong("total_episodes").let { if (rs.wasNull()) null else it },
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