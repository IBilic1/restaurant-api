package hr.algebra.healthyapp.mapper;

import hr.algebra.healthyapp.dto.DishDto;
import hr.algebra.healthyapp.model.Dish;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class, IngredientMapper.class, RestaurantMapper.class})
public interface DishMapper {

    DishDto sourceToDestination(Dish source);

    Dish destinationToSource(DishDto destination);

    List<Dish> sourceToDestination(List<DishDto> destination);

    List<DishDto> destinationToSource(List<Dish> destination);
}
