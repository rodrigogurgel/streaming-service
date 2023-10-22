package br.com.rodrigogurgel.streamingservice.repository

import br.com.rodrigogurgel.streamingservice.domain.Episode

interface EpisodeRepository {
    fun insert(episode: Episode): Episode
}