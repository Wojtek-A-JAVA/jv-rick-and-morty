package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.dto.CharacterSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(CharacterSearchParametersDto searchParametersDto);
}
