package hr.algebra.healthyapp.sheduling;

import hr.algebra.healthyapp.model.Medicine;
import hr.algebra.healthyapp.service.MedicineService;
import hr.algebra.healthyapp.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CalculateQuantityInStockJob {

    private MedicineService medicineService;

    private SupplierService supplierService;

    public CalculateQuantityInStockJob(MedicineService medicineService, SupplierService supplierService) {
        this.medicineService = medicineService;
        this.supplierService = supplierService;
    }

    @Scheduled(cron = "${job.cron.calculateQuantityInStock}")
    public void calculateQuantityInStockJob() {
        log.info("[START] calculateQuantityInStockJob");
        List<Medicine> allMedicines = medicineService.getAllMedicines();
        allMedicines.forEach(medicine -> {
            Double updatedStock = medicine.getQuantityInStock() + supplierService.getStock(medicine);
            medicine.setQuantityInStock(updatedStock);
        });
        medicineService.batchUpdateMedicine(allMedicines);
    }
}
