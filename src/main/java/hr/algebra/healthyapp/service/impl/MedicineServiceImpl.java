package hr.algebra.healthyapp.service.impl;

import hr.algebra.healthyapp.model.Medicine;
import hr.algebra.healthyapp.repository.MedicineRepository;
import hr.algebra.healthyapp.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    private MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public void saveMedicine(Medicine medicine) {
        medicineRepository.saveMedicine(medicine);
    }

    @Override
    public void deleteMedicine(Long medicineId) {
        medicineRepository.deleteMedicine(medicineId);
    }

    @Override
    public Optional<Medicine> getMedicine(Long id) {
        return medicineRepository.getMedicine(id);
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.getAllMedicine();
    }

    @Override
    public void batchUpdateMedicine(List<Medicine> medicines) {
        medicineRepository.batchUpdateMedicine(medicines);
    }
}
