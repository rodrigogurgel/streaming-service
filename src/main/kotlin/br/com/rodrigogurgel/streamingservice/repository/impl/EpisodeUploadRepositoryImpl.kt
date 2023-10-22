package br.com.rodrigogurgel.streamingservice.repository.impl

import br.com.rodrigogurgel.streamingservice.repository.EpisodeUploadRepository
import java.util.UUID
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class EpisodeUploadRepositoryImpl(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
) : EpisodeUploadRepository {
    companion object {
        private const val INSERT_QUERY = """
            insert into episode_upload (episode_id, upload_process_id)
            values (:episode_id, :upload_process_id);
        """
    }

    override fun insert(episodeId: Long, uploadProcessId: UUID) {
        val params = mapOf(
            "episode_id" to episodeId,
            "upload_process_id" to uploadProcessId
        )
        namedParameterJdbcTemplate.update(INSERT_QUERY, params)
    }
}