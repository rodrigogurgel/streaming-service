package br.com.rodrigogurgel.streamingservice.repository.impl

import br.com.rodrigogurgel.streamingservice.domain.Streaming
import br.com.rodrigogurgel.streamingservice.repository.StreamingRepository
import br.com.rodrigogurgel.streamingservice.repository.mapper.StreamingRowMapper
import com.fasterxml.jackson.databind.ObjectMapper
import java.sql.Types
import org.springframework.jdbc.core.SqlParameterValue
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class StreamingRepositoryImpl(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
    private val objectMapper: ObjectMapper,
) : StreamingRepository {

    companion object {
        private const val INSERT_STREAMING_WITH_RETURNING = """
            insert into streaming (title, description, image, alternative_local_titles, release_date, "type", genres, audio_locales,
                       subtitle_locales, content_provider)
            values (:title, :description, :image, (:alternative_local_titles)::jsonb, :release_date, :type, :genres, :audio_locales,
                    :subtitle_locales, :content_provider)
            returning id, title, description, image, alternative_local_titles, release_date, "type", genres, audio_locales, 
                subtitle_locales, content_provider, created_at, updated_at;
        """

        private const val SELECT_STREAMING_BY_ID = """
            select id, title, description, image, alternative_local_titles, release_date, "type", genres, audio_locales, 
                subtitle_locales, content_provider, created_at, updated_at
            from streaming
            where id = :id;
        """
    }

    override fun insert(streaming: Streaming): Streaming {
        val params = buildParams(streaming)
        return namedParameterJdbcTemplate.query(INSERT_STREAMING_WITH_RETURNING, params, StreamingRowMapper()).first()
    }

    override fun selectById(streamingId: Long): Streaming? {
        val params = mapOf("id" to streamingId)
        return namedParameterJdbcTemplate.query(
            SELECT_STREAMING_BY_ID,
            params,
            StreamingRowMapper()
        ).firstOrNull()
    }

    private fun buildParams(streaming: Streaming): Map<String, Any?> {
        return with(streaming) {
            mapOf(
                "id" to id,
                "title" to title,
                "description" to description,
                "image" to image,
                "alternative_local_titles" to objectMapper.writeValueAsString(alternativeLocalTitles),
                "release_date" to releaseDate,
                "type" to type.value,
                "genres" to SqlParameterValue(Types.ARRAY, genres.toTypedArray()),
                "audio_locales" to SqlParameterValue(Types.ARRAY, audioLocales.toTypedArray()),
                "subtitle_locales" to SqlParameterValue(Types.ARRAY, subtitleLocales.toTypedArray()),
                "content_provider" to contentProvider,
            )
        }
    }
}