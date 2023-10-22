package br.com.rodrigogurgel.streamingservice.service

import br.com.rodrigogurgel.streamingservice.cmd.episode.CreateEpisodeCMD
import br.com.rodrigogurgel.streamingservice.dto.episode.EpisodeDTO
import br.com.rodrigogurgel.streamingservice.dto.uploadprocess.UploadProcessDTO
import java.util.UUID
import org.springframework.web.multipart.MultipartFile

interface EpisodeService {
    fun create(streamingId: Long, seasonId: Long, createEpisodeCMD: CreateEpisodeCMD): EpisodeDTO

    fun uploadVideo(episodeId: Long, file: MultipartFile): UploadProcessDTO
}