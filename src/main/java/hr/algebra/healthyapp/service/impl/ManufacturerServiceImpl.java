package hr.algebra.healthyapp.service.impl;

import hr.algebra.healthyapp.model.Manufacturer;
import hr.algebra.healthyapp.repository.ManufacturerRepository;
import hr.algebra.healthyapp.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public void saveManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.saveManufacturer(manufacturer);
    }

    @Override
    public void deleteManufacturer(Long manufacturerId) {
        manufacturerRepository.deleteManufacturer(manufacturerId);
    }

    @Override
    public Optional<Manufacturer> getManufacturer(Long id) {
        return manufacturerRepository.getManufacturer(id);
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return manufacturerRepository.getAllManufacturer();
    }
}
