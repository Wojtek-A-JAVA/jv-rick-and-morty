package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterSearchParametersDto;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Tag(name = "Get")
    @GetMapping()
    @Operation(summary = "Get random character", description = "Get random wiki about one "
            + "character in the universe the animated series Rick & Morty")
    public Character get() {
        Character character = characterService.find();
        return character;
    }

    @Tag(name = "Search")

    @GetMapping("/search")
    @Operation(summary = "Search for a character", description = "Get a list of all characters "
            + "whose name contains the search word")
    public Page<Character> search(CharacterSearchParametersDto parameters,
                                  @Parameter(hidden = true) Pageable pageable) {
        return characterService.search(parameters, pageable);
    }
}
