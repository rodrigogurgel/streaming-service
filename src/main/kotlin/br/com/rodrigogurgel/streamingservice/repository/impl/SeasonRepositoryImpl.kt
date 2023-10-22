package br.com.rodrigogurgel.streamingservice.repository.impl

import br.com.rodrigogurgel.streamingservice.domain.Season
import br.com.rodrigogurgel.streamingservice.repository.SeasonRepository
import br.com.rodrigogurgel.streamingservice.repository.mapper.SeasonRowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class SeasonRepositoryImpl(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
) : SeasonRepository {
    companion object {
        private const val INSERT_SEASON_WITH_RETURNING = """
            insert into season (title, description, streaming_id, total_episodes, release_date)
            values (:title, :description, :streaming_id, :total_episodes, :release_date)
            returning id, streaming_id, title, description, total_episodes, release_date, created_at, updated_at;
        """
    }

    override fun insert(season: Season): Season {
        val params = buildParams(season)
        return namedParameterJdbcTemplate.query(INSERT_SEASON_WITH_RETURNING, params, SeasonRowMapper()).first()
    }

    private fun buildParams(season: Season): Map<String, Any?> {
        return with(season) {
            mapOf(
                "id" to id,
                "streaming_id" to streamingId,
                "title" to title,
                "description" to description,
                "total_episodes" to totalEpisodes,
                "release_date" to releaseDate,
                "created_at" to createdAt,
                "updated_at" to updatedAt,
            )
        }
    }
}