package hr.algebra.healthyapp.mapper;

import hr.algebra.healthyapp.dto.IngredientDto;
import hr.algebra.healthyapp.model.Ingredient;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IngredientMapper {

    IngredientDto sourceToDestination(Ingredient source);

    Ingredient destinationToSource(IngredientDto destination);

    List<Ingredient> sourceToDestination(List<IngredientDto> destination);

    List<IngredientDto> destinationToSource(List<Ingredient> destination);
}

