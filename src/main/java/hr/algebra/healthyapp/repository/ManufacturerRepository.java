package hr.algebra.healthyapp.repository;

import hr.algebra.healthyapp.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerRepository {

    void saveManufacturer(Manufacturer manufacturer);

    void deleteManufacturer(Long manufacturerId);

    Optional<Manufacturer> getManufacturer(Long id);

    List<Manufacturer> getAllManufacturer();
}
