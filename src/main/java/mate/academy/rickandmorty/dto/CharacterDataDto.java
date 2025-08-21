package mate.academy.rickandmorty.dto;

import java.util.ArrayList;
import lombok.Data;

@Data
public class CharacterDataDto {
    private CharacterInfoDto info;
    private ArrayList<CharacterResultsDto> results;
}
