package mate.academy.rickandmorty.config;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterInfoDto;
import mate.academy.rickandmorty.dto.CharacterResultsDto;
import mate.academy.rickandmorty.integration.CharacterApiClient;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    private static final String URL_INFO = "https://rickandmortyapi.com/api/character";
    private static final String URL_DATA = "https://rickandmortyapi.com/api/character?page=%s";
    private final CharacterApiClient characterDtoResponse;
    private final CharacterRepository characterRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        CharacterInfoDto characterInfoDto =
                characterDtoResponse.getDtoResponse(URL_INFO).getInfo();
        if (characterRepository.count() > 0) {
            return;
        }
        for (int i = 1; i <= characterInfoDto.getPages(); i++) {
            ArrayList<CharacterResultsDto> characterDtoResults =
                    characterDtoResponse.getDtoResponse(URL_DATA.formatted(i)).getResults();

            for (CharacterResultsDto dto : characterDtoResults) {
                Character character = new Character();
                if (characterRepository.existsByExternalId(Integer.toString(dto.getId()))) {
                    continue;
                }
                character.setExternalId(Integer.toString(dto.getId()));
                character.setName(dto.getName());
                character.setStatus(dto.getStatus());
                character.setSpecies(dto.getSpecies());
                character.setType(dto.getType());
                character.setGender(dto.getGender());
                character.setOrigin(dto.getOrigin());
                character.setLocation(dto.getLocation());
                character.setImage(dto.getImage());
                character.setUrl(dto.getUrl());
                character.setCreated(dto.getCreated());
                characterRepository.save(character);
            }
        }
    }
}
