package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.HealthyappApplication;
import hr.algebra.healthyapp.model.Medicine;
import hr.algebra.healthyapp.model.Order;
import hr.algebra.healthyapp.model.Prescription;
import hr.algebra.healthyapp.model.User;
import hr.algebra.healthyapp.repository.OrderRepository;
import hr.algebra.healthyapp.repository.PrescriptionRepository;
import hr.algebra.healthyapp.repository.UserRepository;
import hr.algebra.healthyapp.user.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HealthyappApplication.class)
@ActiveProfiles("unit")
public class PrescriptionServiceTest {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PrescriptionService prescriptionService;

    private Prescription prescription;

    private User doctor;

    private User patient;

    @BeforeEach
    public void beforeEach() {
        doctor = User.builder()
                .firstName("Doctor")
                .lastName("Doe")
                .email("doctor@example.com")
                .password("password")
                .role(Role.ADMIN)
                .build();

        patient = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("password")
                .role(Role.USER)
                .build();

        prescription = Prescription.builder()
                .patient(patient)
                .doctor(doctor)
                .id(1L)
                .build();
    }

    @Test
    void savePrescription_ValidPrescription_ShouldSavePrescription() {
        // Act
        Prescription newPrescription = prescriptionService.savePrescription(this.prescription, prescription.getDoctor().getEmail());

        // Assert
        assertNotNull(this.prescription.getId());
        Optional<Prescription> savedPrescription = prescriptionService.getPrescription(newPrescription.getId());
        assertTrue(savedPrescription.isPresent());
    }

    @Test
    void updatePrescription_ValidPrescription_ShouldUpdatePrescription() {
        // Arrange
        Order order = Order.builder()
                .id(1L)
                .amount(10D)
                .description("DESC")
                .doseGap(5D)
                .medicine(Medicine.builder().id(1L).name("Linex").manufacturerId(1L).description("D").build())
                .build();
        Prescription prescriptionToUpdate = Prescription.builder()
                .doctor(doctor)
                .patient(patient)
                .id(1L)
                .orders(Arrays.asList(order)).build();
        // Act
        Prescription updatedPrescription = prescriptionService.savePrescription(prescriptionToUpdate, this.prescription.getDoctor().getEmail());

        // Assert
        Optional<Prescription> savedPrescription = prescriptionService.getPrescription(updatedPrescription.getId());
        assertTrue(savedPrescription.isPresent());
        List<Order> orders = savedPrescription.get().getOrders();
        assertEquals(orders.size(), 1);
    }


    @Test
    void deleteAppointment_shouldDeleteAppointment() {
        // Arrange
        prescriptionService.savePrescription(prescription, prescription.getDoctor().getEmail());

        // Act
        prescriptionService.deletePrescription(prescription.getId());

        // Assert
        assertFalse(prescriptionService.getPrescriptionsByUser(prescription.getDoctor().getEmail()).contains(prescription));
    }

    @Test
    void getAppointment_shouldReturnOptionalAppointment() {
        // Arrange
        Prescription newPrescription = prescriptionService.savePrescription(this.prescription, doctor.getEmail());

        // Act
        Optional<Prescription> result = prescriptionService.getPrescription(newPrescription.getId());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(prescription.getId(), result.get().getId());
    }

    @Test
    void getPrescriptionByDoctor_shouldReturnListOfPrescription() {
        // Act
        List<Prescription> result = prescriptionService.getPrescriptionsByUser(doctor.getEmail());

        // Assert
        assertEquals(3, result.size());
        assertEquals(result.get(0).getDoctor().getEmail(), doctor.getEmail());
    }

    @Test
    void getPrescriptionByUser_shouldReturnListOfPrescription() {
        // Act
        List<Prescription> result = prescriptionService.getPrescriptionsByUser(patient.getEmail());

        // Assert
        assertEquals(2, result.size());
        assertEquals(result.get(0).getPatient().getEmail(), patient.getEmail());
    }
}
