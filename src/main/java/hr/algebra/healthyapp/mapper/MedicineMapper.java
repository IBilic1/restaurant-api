package hr.algebra.healthyapp.mapper;

import hr.algebra.healthyapp.dto.MedicineDto;
import hr.algebra.healthyapp.model.Medicine;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MedicineMapper {

    MedicineMapper INSTANCE = Mappers.getMapper(MedicineMapper.class);

    MedicineDto sourceToDestination(Medicine source);

    Medicine destinationToSource(MedicineDto destination);

    List<Medicine> sourceToDestination(List<MedicineDto> destination);

    List<MedicineDto> destinationToSource(List<Medicine> destination);
}
