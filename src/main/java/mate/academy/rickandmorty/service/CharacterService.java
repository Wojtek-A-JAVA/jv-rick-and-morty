package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    Character find();

    List<Character> search(String name, Pageable pageable);
}
