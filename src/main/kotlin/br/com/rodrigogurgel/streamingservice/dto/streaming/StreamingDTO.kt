package br.com.rodrigogurgel.streamingservice.dto.streaming

import br.com.rodrigogurgel.streamingservice.domain.StreamingTypeEnum
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.OffsetDateTime

@Schema(
    description = "Model of response streaming",
    name = "streaming-dto"
)
data class StreamingDTO(
    @Schema(
        description = "Streaming ID",
        example = "1"
    )
    val id: Long,
    @Schema(
        description = "Streaming title",
        example = "Title of Streaming"
    )
    @field:NotBlank
    @field:Size(min = 1, max = 100)
    val title: String,
    @Schema(
        description = "Streaming description",
        example = "This is a streaming description"

    )
    @field:Size(min = 10, max = 1000)
    @field:NotBlank
    val description: String,

    @Schema(
        description = "Image link",
    )
    val image: String?,
    @Schema(
        description = "Alternative titles to different languages",
        example = """{ "pt-BR": "Título em português do Brasil" }"""
    )
    val alternativeLocalTitles: Map<String, String>,
    @Schema(
        description = "Streaming release date",
        example = "1996-09-02T17:06:06.910Z"
    )
    val releaseDate: OffsetDateTime?,
    @Schema(
        description = "Streaming category type",
        examples = ["MOVIE", "ANIME", "SERIES"]
    )
    val type: StreamingTypeEnum,
    @Schema(
        description = "List of streaming genres",
        example = """["Action","Adventure","Comedy","Crime and mystery","Fantasy","Historical","Horror","Romance"]""",
    )
    val genres: List<String>,
    @Schema(
        description = "List of audios streaming are available",
        example = """["en","pt-BR"]""",
    )
    val audioLocales: List<String>,
    @Schema(
        description = "List of subtitles streaming are available",
        example = """["en","pt-BR"]""",
    )
    val subtitleLocales: List<String>,
    @Schema(
        description = "Streaming are provider for",
        example = "SQUARE ENIX",
    )
    val contentProvider: String,
)