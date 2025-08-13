package mate.academy.rickandmorty.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CharacterSearchParametersDto(
        @Schema(name = "name", type = "string") String[] name) {
}
