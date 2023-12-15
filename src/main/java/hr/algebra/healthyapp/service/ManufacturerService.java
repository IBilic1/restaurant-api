package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    void saveManufacturer(Manufacturer manufacturer);

    void deleteManufacturer(Long manufacturerId);

    Optional<Manufacturer> getManufacturer(Long id);

    List<Manufacturer> getAllManufacturer();
}
