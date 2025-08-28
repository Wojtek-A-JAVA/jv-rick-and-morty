package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
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
    @GetMapping("/random")
    @Operation(summary = "Get random character", description = "Get random wiki about one "
            + "character in the universe the animated series Rick & Morty")
    public CharacterDto random() {
        return characterService.getRandomCharacter();
    }

    @Tag(name = "Search")
    @GetMapping("/search")
    @Operation(summary = "Search for a character", description = "Get a list of all characters "
            + "whose name contains the search letters")
    public List<CharacterDto> search(@RequestParam @Size(min = 1,
            message = "The search word can have min. 1 characters.") String name) {
        return characterService.search(name);
    }
}
