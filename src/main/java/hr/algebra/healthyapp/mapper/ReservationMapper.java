package hr.algebra.healthyapp.mapper;


import hr.algebra.healthyapp.dto.ReservationDto;
import hr.algebra.healthyapp.model.Reservation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserMapper.class, DeskMapper.class})
public interface ReservationMapper {

    ReservationDto sourceToDestination(Reservation source);

    Reservation destinationToSource(ReservationDto destination);

    List<Reservation> sourceToDestination(List<ReservationDto> destination);

    List<ReservationDto> destinationToSource(List<Reservation> destination);
}
