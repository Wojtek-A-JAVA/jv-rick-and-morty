package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public Character find() {
        long databaseSize = characterRepository.count();
        long randomId = (long) ((Math.random() * (databaseSize - 1)) + 1);
        Character character = characterRepository.findById(randomId).orElseThrow(
                () -> new EntityNotFoundException("Can't generate random wiki about character"));
        return character;
    }

    @Override
    public List<Character> search(String name, Pageable pageable) {

        return characterRepository.findByNameContainingIgnoreCase(name, pageable)
                .stream()
                .toList();
    }
}
