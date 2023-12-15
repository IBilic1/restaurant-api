package hr.algebra.healthyapp.controller;

import hr.algebra.healthyapp.dto.MedicineDto;
import hr.algebra.healthyapp.mapper.MedicineMapper;
import hr.algebra.healthyapp.service.MedicineService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicine")
@Secured("ADMIN")
public class MedicineController {

    private MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    public ResponseEntity<List<MedicineDto>> getAllMedicine() {
        return ResponseEntity.ok().body(MedicineMapper.INSTANCE.destinationToSource(medicineService.getAllMedicines()));
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<MedicineDto> getMedicine(@PathVariable Long medicineId) {
        return ResponseEntity.ok().body(medicineService.getMedicine(medicineId)
                .map(MedicineMapper.INSTANCE::sourceToDestination)
                .orElseThrow(EntityExistsException::new));
    }

    @PostMapping
    public ResponseEntity<Void> createMedicine(@RequestBody MedicineDto medicine) {
        medicineService.saveMedicine(MedicineMapper.INSTANCE.destinationToSource(medicine));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateMedicine(@RequestBody MedicineDto medicine) {
        medicineService.saveMedicine(MedicineMapper.INSTANCE.destinationToSource(medicine));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long medicineId) {
        medicineService.deleteMedicine(medicineId);
        return ResponseEntity.ok().build();
    }
}
