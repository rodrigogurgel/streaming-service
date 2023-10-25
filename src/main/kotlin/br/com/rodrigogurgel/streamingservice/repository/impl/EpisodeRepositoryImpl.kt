package br.com.rodrigogurgel.streamingservice.repository.impl

import br.com.rodrigogurgel.streamingservice.domain.Episode
import br.com.rodrigogurgel.streamingservice.repository.EpisodeRepository
import br.com.rodrigogurgel.streamingservice.repository.mapper.EpisodeRowMapper
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class EpisodeRepositoryImpl(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
    private val objectMapper: ObjectMapper,
) : EpisodeRepository {

    companion object {
        private const val INSERT_EPISODE_WITH_RETURNING = """
            insert into episode (season_id, title, metadata, release_date)
            values (:season_id, :title, (:metadata)::jsonb, :release_date)
            returning id, season_id, title, metadata, release_date, created_at, updated_at;
        """

        private const val SELECT_BY_ID = """
            select id, season_id, title, metadata, release_date, created_at, updated_at
            from episode
            where id = :id;
        """

        private const val UPDATE_WITH_RETURNING = """
            update episode
            set season_id    = :season_id,
                title        = :title,
                metadata     = (:metadata)::jsonb,
                release_date = :release_date,
                updated_at   = now()
            where id = :id
            returning id, season_id, title, metadata, release_date, created_at, updated_at;
        """
    }

    override fun insert(episode: Episode): Episode {
        val params = buildParams(episode)
        return namedParameterJdbcTemplate.query(INSERT_EPISODE_WITH_RETURNING, params, EpisodeRowMapper()).first()
    }

    override fun selectById(episodeId: Long): Episode {
        val params = mapOf("id" to episodeId)
        return namedParameterJdbcTemplate.query(SELECT_BY_ID, params, EpisodeRowMapper()).first()
    }

    override fun update(episode: Episode): Episode {
        val params = buildParams(episode)
        return namedParameterJdbcTemplate.query(UPDATE_WITH_RETURNING, params, EpisodeRowMapper()).first()
    }

    fun buildParams(episode: Episode): Map<String, Any?> {
        return with(episode) {
            mapOf(
                "id" to id,
                "season_id" to seasonId,
                "title" to title,
                "metadata" to objectMapper.writeValueAsString(metadata),
                "release_date" to releaseDate,
                "created_at" to createdAt,
                "updated_at" to updatedAt,
            )
        }
    }
}