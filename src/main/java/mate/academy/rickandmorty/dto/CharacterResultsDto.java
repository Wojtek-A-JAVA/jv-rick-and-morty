package mate.academy.rickandmorty.dto;

import java.util.LinkedHashMap;
import lombok.Data;

@Data
public class CharacterResultsDto {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private LinkedHashMap origin;
    private LinkedHashMap location;
    private String image;
    private String[] episode;
    private String url;
    private String created;

}
