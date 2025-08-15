package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public Character find() {
        long databaseSize = characterRepository.count();
        int randomRecord = (int) (Math.random() * databaseSize);

        Page<Character> characterPage =
                characterRepository.findAll(PageRequest.of(randomRecord, 1));
        if (!characterPage.hasContent()) {
            throw new EntityNotFoundException("Can't generate random wiki about character");
        }
        Character character = characterPage.getContent().get(0);
        return character;
    }

    @Override
    public List<Character> search(String name, Pageable pageable) {

        return characterRepository.findByNameContainingIgnoreCase(name, pageable)
                .stream()
                .toList();
    }
}
