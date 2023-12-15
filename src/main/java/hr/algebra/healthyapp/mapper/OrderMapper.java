package hr.algebra.healthyapp.mapper;

import hr.algebra.healthyapp.dto.OrderDto;
import hr.algebra.healthyapp.model.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {MedicineMapper.class})
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto sourceToDestination(Order source);

    Order destinationToSource(OrderDto destination);

    List<Order> sourceToDestination(List<OrderDto> destination);

    List<OrderDto> destinationToSource(List<Order> destination);
}
