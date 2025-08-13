package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterSearchParametersDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.CharacterSpecificationBuilderImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterSpecificationBuilderImpl characterSpecificationBuilder;

    @Override
    public Character find() {
        long databaseSize = characterRepository.count();
        long randomId = (long) ((Math.random() * (databaseSize - 1)) + 1);
        Character character = characterRepository.findById(randomId).orElseThrow(
                () -> new EntityNotFoundException("Can't generate random wiki about character"));
        return character;
    }

    @Override
    public Page<Character> search(CharacterSearchParametersDto parameters, Pageable pageable) {
        Specification<Character> characterSpecification =
                characterSpecificationBuilder.build(parameters);
        List<Character> characterList = characterRepository.findAll(characterSpecification)
                .stream().toList();
        return new PageImpl<>(characterList, pageable, characterList.size());
    }
}
