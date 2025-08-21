package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public CharacterDto find() {
        Random random = new Random();
        ModelMapper modelMapper = new ModelMapper();
        List<CharacterDto> characterDtoList = characterRepository.findAll()
                .stream()
                .map(ch -> modelMapper.map(ch, CharacterDto.class))
                .toList();
        return characterDtoList.get(random.nextInt(characterDtoList.size()));
    }

    @Override
    public List<CharacterDto> search(String name) {
        ModelMapper modelMapper = new ModelMapper();
        return characterRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ch -> modelMapper.map(ch, CharacterDto.class))
                .toList();
    }
}
