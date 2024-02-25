package hr.algebra.healthyapp.mapper;

import hr.algebra.healthyapp.dto.OrderDto;
import hr.algebra.healthyapp.model.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class, DishMapper.class})
public interface OrderMapper {

    OrderDto sourceToDestination(Order source);

    Order destinationToSource(OrderDto destination);

    List<Order> sourceToDestination(List<OrderDto> destination);

    List<OrderDto> destinationToSource(List<Order> destination);
}
