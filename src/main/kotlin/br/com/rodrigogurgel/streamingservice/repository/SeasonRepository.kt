package br.com.rodrigogurgel.streamingservice.repository

import br.com.rodrigogurgel.streamingservice.domain.Season

interface SeasonRepository {
    fun insert(season: Season): Season
    fun selectByIdAndStreamingId(seasonId: Long, streamingId: Long): Season?
}
