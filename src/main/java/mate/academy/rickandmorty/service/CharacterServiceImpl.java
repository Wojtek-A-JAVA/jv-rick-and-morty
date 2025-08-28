package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public CharacterDto getRandomCharacter() {
        Random random = new Random();
        List<CharacterDto> characterDtoList = characterRepository.findAll()
                .stream()
                .map(ch -> modelMapper.map(ch, CharacterDto.class))
                .toList();
        return characterDtoList.get(random.nextInt(characterDtoList.size()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CharacterDto> search(String name) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Search name cannot be null or empty");
        }
        return characterRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ch -> modelMapper.map(ch, CharacterDto.class))
                .toList();
    }
}
