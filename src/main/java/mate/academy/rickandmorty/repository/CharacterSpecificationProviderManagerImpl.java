package mate.academy.rickandmorty.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterSpecificationProviderManagerImpl implements
        SpecificationProviderManager<Character> {

    private final List<SpecificationProvider<Character>> characterSpecificationProviders;

    @Override
    public SpecificationProvider<Character> getSpecificationProvider(String key) {
        return characterSpecificationProviders.stream()
                .filter(ch -> ch.getKey().equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("No specification provider found for " + key));
    }
}
