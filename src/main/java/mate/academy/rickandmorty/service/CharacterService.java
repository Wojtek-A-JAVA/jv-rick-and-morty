package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.CharacterSearchParametersDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    Character find();

    Page<Character> search(CharacterSearchParametersDto parameters, Pageable pageable);
}
