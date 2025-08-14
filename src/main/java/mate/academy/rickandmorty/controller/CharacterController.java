package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/character")
    @Operation(summary = "Search for a character", description = "Get a list of all characters "
            + "whose name contains the search letters")
    public List<Character> search(@RequestParam(required = false) String name,
                                  @Parameter(hidden = true) Pageable pageable) {
        return characterService.search(name, pageable);
    }
}
