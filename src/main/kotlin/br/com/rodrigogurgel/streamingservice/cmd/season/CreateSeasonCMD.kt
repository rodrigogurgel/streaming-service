package br.com.rodrigogurgel.streamingservice.cmd.season

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.OffsetDateTime

@Schema(
    description = "New season",
)
data class CreateSeasonCMD(
    @Schema(
        description = "Streaming title",
        example = " Title of Streaming"
    )
    @field:NotBlank
    @field:Size(max = 100)
    val title: String,
    @Schema(
        description = "Streaming description",
        example = "This is a streaming description"

    )
    @field:Size(min = 10, max = 1000)
    @field:NotBlank
    val description: String,
    @Schema(
        description = "Streaming release date",
        example = "1996-09-02T17:06:06.910Z"
    )
    val releaseDate: OffsetDateTime?,
    @Schema(
        description = "Streaming release date",
        example = "12"
    )
    @field:Min(1)
    val totalEpisodes: Long?,
)
