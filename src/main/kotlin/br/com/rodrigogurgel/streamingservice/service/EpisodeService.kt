package br.com.rodrigogurgel.streamingservice.service

import br.com.rodrigogurgel.streamingservice.cmd.episode.CreateEpisodeCMD
import br.com.rodrigogurgel.streamingservice.cmd.episode.PathEpisodeMetadataCMD
import br.com.rodrigogurgel.streamingservice.dto.episode.EpisodeDTO

interface EpisodeService {
    fun create(streamingId: Long, seasonId: Long, createEpisodeCMD: CreateEpisodeCMD): EpisodeDTO

    fun fetchById(episodeId: Long): EpisodeDTO

    fun pathEpisodeMetadata(episodeId: Long, pathEpisodeMetadataCMD: PathEpisodeMetadataCMD)
}