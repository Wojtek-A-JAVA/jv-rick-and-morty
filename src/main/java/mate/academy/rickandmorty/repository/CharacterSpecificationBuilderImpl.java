package mate.academy.rickandmorty.repository;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterSearchParametersDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterSpecificationBuilderImpl implements SpecificationBuilder<Character> {

    private final SpecificationProviderManager<Character> characterSpecificationProviderManager;

    @Override
    public Specification<Character> build(CharacterSearchParametersDto searchParametersDto) {
        Specification<Character> specification = ((root, query, criteriaBuilder) ->
                criteriaBuilder.conjunction());
        if (searchParametersDto.name() != null && searchParametersDto.name().length > 0) {
            specification = specification.and(characterSpecificationProviderManager
                    .getSpecificationProvider("name")
                    .getSpecification(searchParametersDto.name()));
        }
        return specification;
    }
}
