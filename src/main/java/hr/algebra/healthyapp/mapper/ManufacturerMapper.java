package hr.algebra.healthyapp.mapper;

import hr.algebra.healthyapp.dto.ManufacturerDto;
import hr.algebra.healthyapp.model.Manufacturer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ManufacturerMapper {

    ManufacturerMapper INSTANCE = Mappers.getMapper(ManufacturerMapper.class);

    ManufacturerDto sourceToDestination(Manufacturer source);

    Manufacturer destinationToSource(ManufacturerDto destination);

    List<Manufacturer> sourceToDestination(List<ManufacturerDto> destination);

    List<ManufacturerDto> destinationToSource(List<Manufacturer> destination);
}
