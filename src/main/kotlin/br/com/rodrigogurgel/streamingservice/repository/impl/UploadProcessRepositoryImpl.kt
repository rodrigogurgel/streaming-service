package br.com.rodrigogurgel.streamingservice.repository.impl

import br.com.rodrigogurgel.streamingservice.domain.UploadProcess
import br.com.rodrigogurgel.streamingservice.domain.UploadProcessStatusEnum
import br.com.rodrigogurgel.streamingservice.repository.UploadProcessRepository
import br.com.rodrigogurgel.streamingservice.repository.mapper.UploadProcessRowMapper
import java.util.UUID
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class UploadProcessRepositoryImpl(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
) : UploadProcessRepository {

    companion object {
        private const val INSERT_QUERY_WITH_RETURNING = """
            insert into upload_process (id, status)
            values (:id, :status)
            returning id, status, created_at, updated_at;
        """

        private const val UPDATE_STATUS_BY_ID = """
            update upload_process
            set status = :status
            where id = :id;
        """
    }

    override fun insert(uploadProcess: UploadProcess): UploadProcess {
        val params = buildParams(uploadProcess)
        return namedParameterJdbcTemplate.query(INSERT_QUERY_WITH_RETURNING, params, UploadProcessRowMapper()).first()
    }

    override fun updateStatus(uploadProcessId: UUID, status: UploadProcessStatusEnum) {
        val params = mapOf(
            "id" to uploadProcessId,
            "status" to status.value
        )
        namedParameterJdbcTemplate.update(UPDATE_STATUS_BY_ID, params)
    }

    private fun buildParams(uploadProcess: UploadProcess): Map<String, Any?> {
        return with(uploadProcess) {
            mapOf(
                "id" to id,
                "status" to status.value,
                "created_at" to createdAt,
                "upload_at" to updatedAt
            )
        }
    }
}