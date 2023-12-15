package hr.algebra.healthyapp.mapper;

import hr.algebra.healthyapp.dto.PrescriptionDto;
import hr.algebra.healthyapp.model.Prescription;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {OrderMapper.class})
public interface PrescriptionMapper {

    PrescriptionDto sourceToDestination(Prescription source);

    Prescription destinationToSource(PrescriptionDto destination);

    List<Prescription> sourceToDestination(List<PrescriptionDto> destination);

    List<PrescriptionDto> destinationToSource(List<Prescription> destination);
}
