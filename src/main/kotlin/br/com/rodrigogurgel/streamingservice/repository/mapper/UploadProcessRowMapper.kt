package br.com.rodrigogurgel.streamingservice.repository.mapper

import br.com.rodrigogurgel.streamingservice.domain.UploadProcess
import br.com.rodrigogurgel.streamingservice.domain.UploadProcessStatusEnum
import java.sql.ResultSet
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.UUID
import org.springframework.jdbc.core.RowMapper

class UploadProcessRowMapper: RowMapper<UploadProcess> {
    override fun mapRow(rs: ResultSet, rowNum: Int): UploadProcess {
        return UploadProcess(
            id = UUID.fromString(rs.getString("id")),
            status = UploadProcessStatusEnum.valueOf(rs.getString("status")),
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