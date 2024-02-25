package hr.algebra.healthyapp.mapper;

import hr.algebra.healthyapp.dto.DeskDto;
import hr.algebra.healthyapp.model.Desk;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {RestaurantMapper.class})
public interface DeskMapper {

    DeskDto sourceToDestination(Desk source);

    Desk destinationToSource(DeskDto destination);

    List<Desk> sourceToDestination(List<DeskDto> destination);

    List<DeskDto> destinationToSource(List<Desk> destination);
}
