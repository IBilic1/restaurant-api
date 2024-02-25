package hr.algebra.healthyapp.mapper;

import hr.algebra.healthyapp.dto.RestaurantDto;
import hr.algebra.healthyapp.model.Restaurant;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class})
public interface RestaurantMapper {

    RestaurantDto sourceToDestination(Restaurant source);

    Restaurant destinationToSource(RestaurantDto destination);

    List<Restaurant> sourceToDestination(List<RestaurantDto> destination);

    List<RestaurantDto> destinationToSource(List<Restaurant> destination);
}
