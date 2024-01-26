package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.HealthyappApplication;
import hr.algebra.healthyapp.model.Medicine;
import hr.algebra.healthyapp.repository.MedicineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HealthyappApplication.class)
@ActiveProfiles("unit")
public class MedicineServiceTest {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private MedicineService medicineService;

    private Medicine medicine;


    @BeforeEach
    public void beforeEach() {
        medicine = Medicine.builder()
                .description("Desc")
                .manufacturerId(1L)
                .name("Brufen")
                .build();
    }

    @Test
    void saveMedicine_ValidMedicine_ShouldSaveMedicine() {
        // Act
        Long id = medicineRepository.saveMedicine(medicine);

        // Assert
        assertNotNull(id);
        Optional<Medicine> savedAppointment = medicineRepository.getMedicine(id);
        assertTrue(savedAppointment.isPresent());
    }

    @Test
    void updateMedicine_ValidMedicine_ShouldUpdateMedicine() {
        // Arrange
        Medicine medicineToUpdate = Medicine.builder()
                .description("Desc")
                .manufacturerId(1L)
                .name("Brufen")
                .build();
        ;
        medicineToUpdate.setDescription("DESC UPDATED");
        medicineToUpdate.setId(1L);

        Long id = medicineRepository.saveMedicine(medicineToUpdate);

        Optional<Medicine> savedAppointment = medicineRepository.getMedicine(id);
        assertTrue(savedAppointment.isPresent());
        assertEquals(savedAppointment.get().getDescription(), medicineToUpdate.getDescription());
    }

    @Test()
    void deleteMedicine_shouldNotDeleteMedicine() {
        // Arrange
        Long id = medicineRepository.saveMedicine(medicine);

        // Act
        medicineService.deleteMedicine(id);

        // Assert
        assertThrows(EmptyResultDataAccessException.class, () -> medicineService.getMedicine(id));
    }

    @Test
    void getMedicine_shouldReturnOptionalMedicine() {
        // Arrange
        Long id = medicineRepository.saveMedicine(medicine);
        medicine.setId(id);

        // Act
        Optional<Medicine> result = medicineService.getMedicine(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(medicine, result.get());
    }
}
