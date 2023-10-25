package br.com.rodrigogurgel.streamingservice.service.impl

import br.com.rodrigogurgel.streamingservice.cmd.episode.CreateEpisodeCMD
import br.com.rodrigogurgel.streamingservice.cmd.episode.PathEpisodeMetadataCMD
import br.com.rodrigogurgel.streamingservice.config.properties.HashProperties
import br.com.rodrigogurgel.streamingservice.config.properties.VodProperties
import br.com.rodrigogurgel.streamingservice.converter.cmd.EpisodeCMDConverter
import br.com.rodrigogurgel.streamingservice.converter.dto.EpisodeDTOConverter
import br.com.rodrigogurgel.streamingservice.domain.Episode
import br.com.rodrigogurgel.streamingservice.dto.episode.EpisodeDTO
import br.com.rodrigogurgel.streamingservice.exception.SeasonNotFoundException
import br.com.rodrigogurgel.streamingservice.repository.EpisodeRepository
import br.com.rodrigogurgel.streamingservice.repository.SeasonRepository
import br.com.rodrigogurgel.streamingservice.service.EpisodeService
import java.time.Duration
import java.time.Instant
import org.springframework.stereotype.Service

@Service
class EpisodeServiceImpl(
    private val episodeRepository: EpisodeRepository,
    private val seasonRepository: SeasonRepository,
    private val vodProperties: VodProperties,
    private val hashProperties: HashProperties,
) : EpisodeService {
    override fun create(streamingId: Long, seasonId: Long, createEpisodeCMD: CreateEpisodeCMD): EpisodeDTO {
        seasonRepository.selectByIdAndStreamingId(seasonId, streamingId) ?: throw SeasonNotFoundException(
            seasonId,
            streamingId
        )

        val episode = EpisodeCMDConverter.toDomain(seasonId, createEpisodeCMD)
        val episodeDb = episodeRepository.insert(episode)
        return EpisodeDTOConverter.from(episodeDb)
    }

    override fun fetchById(episodeId: Long): EpisodeDTO {
        val episodeDb = episodeRepository.selectById(episodeId)
        val link = buildLink(episodeDb)
        return EpisodeDTOConverter.from(episodeDb, link)
    }

    override fun pathEpisodeMetadata(episodeId: Long, pathEpisodeMetadataCMD: PathEpisodeMetadataCMD) {
        val episodeDb = episodeRepository.selectById(episodeId)
        val episodeMetadata = episodeDb.metadata.let {
            it.copy(
                filePath = pathEpisodeMetadataCMD.filePath ?: it.filePath,
                qualities = pathEpisodeMetadataCMD.qualities ?: it.qualities,
            )
        }
        episodeRepository.update(episodeDb.copy(metadata = episodeMetadata))
    }

    private fun buildLink(episode: Episode): String? {
        if (!episode.metadata.filePath.isNullOrBlank() && episode.metadata.qualities.isEmpty()) return null

        val now = Instant.now().plus(Duration.ofHours(1)).toEpochMilli().toDouble() / 1000
        val expires = Math.round(now)
        val token = hashProperties.hash("$expires ${vodProperties.ip} ${hashProperties.secret}")
        val id = hashProperties.hash("${hashProperties.secret}${episode.id}")

        val qualities = episode.metadata.qualities.joinToString(prefix = ",", postfix = ",", separator = ",") {
            "_${it}p.mp4"
        }

        val subtitles = episode.metadata.subtitles?.joinToString(postfix = ",", separator = ",") {
            "lang/$it/subtitles_${it}.srt"
        }

        return "${vodProperties.baseUrl}$id/${episode.metadata.filePath}/$token/$expires/${qualities + subtitles}${vodProperties.suffix}"
    }
}