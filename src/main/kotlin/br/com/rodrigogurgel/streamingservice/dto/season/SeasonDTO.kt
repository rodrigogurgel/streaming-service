package br.com.rodrigogurgel.streamingservice.dto.season

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.OffsetDateTime

@Schema(
    description = "Model of response season",
    name = "season-dto"
)
data class SeasonDTO(
    @Schema(
        description = "Season ID",
    )
    val id: Long,
    @Schema(
        description = "Streaming ID",
    )
    val streamingId: Long,
    @Schema(
        description = "Season title",
        example = "Title of season"
    )
    @field:NotBlank
    @field:Size(min = 1, max = 100)
    val title: String,
    @Schema(
        description = "Season description",
        example = "This is a season description"

    )
    @field:Size(min = 10, max = 1000)
    @field:NotBlank
    val description: String,
    @Schema(
        description = "Total of episodes",
    )
    val totalEpisodes: Long?,
    @Schema(
        description = "Season release date",
        example = "1996-09-02T17:06:06.910Z"
    )
    val releaseDate: OffsetDateTime?
)
