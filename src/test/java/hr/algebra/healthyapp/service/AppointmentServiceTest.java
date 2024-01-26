package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.HealthyappApplication;
import hr.algebra.healthyapp.model.Appointment;
import hr.algebra.healthyapp.model.User;
import hr.algebra.healthyapp.repository.AppointmentRepository;
import hr.algebra.healthyapp.repository.UserRepository;
import hr.algebra.healthyapp.user.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HealthyappApplication.class)
@ActiveProfiles("unit")
class AppointmentServiceTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentService appointmentService;

    private User doctor;

    private User patient;

    private Appointment appointment;

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

        appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
    }

    @Test
    void saveAppointment_ValidAppointment_ShouldSaveAppointment() {
        // Act
        appointmentService.saveAppointment(appointment, doctor.getEmail());

        // Assert
        assertNotNull(appointment.getId());
        Optional<Appointment> savedAppointment = appointmentRepository.findById(appointment.getId());
        assertTrue(savedAppointment.isPresent());
    }

    @Test
    void updateAppointment_ValidAppointment_ShouldUpdateAppointment() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setAddress("Ilica 12345");
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        // Act
        appointmentService.saveAppointment(appointment, doctor.getEmail());

        // Assert
        Optional<Appointment> savedAppointment = appointmentRepository.findById(appointment.getId());
        assertTrue(savedAppointment.isPresent());
        assertEquals(savedAppointment.get().getAddress(), appointment.getAddress());
    }


    @Test
    void deleteAppointment_shouldDeleteAppointment() {
        // Arrange
        appointmentService.saveAppointment(appointment, appointment.getDoctor().getEmail());
        Long appointmentId = appointment.getId();

        // Act
        appointmentService.deleteAppointment(appointmentId);

        // Assert
        assertFalse(appointmentService.getAppointmentsByUser(appointment.getDoctor().getEmail()).contains(appointment));
    }

    @Test
    void getAppointment_shouldReturnOptionalAppointment() {
        // Arrange
        appointmentService.saveAppointment(appointment, doctor.getEmail());
        Long id = appointment.getId();

        // Act
        Optional<Appointment> result = appointmentService.getAppointment(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(appointment, result.get());
    }

    @Test
    void getAppointmentsByDoctor_shouldReturnListOfAppointments() {
        // Act
        List<Appointment> result = appointmentService.getAppointmentsByUser(doctor.getEmail());

        // Assert
        assertEquals(3, result.size());
        assertEquals(result.get(0).getDoctor().getEmail(), doctor.getEmail());
    }

    @Test
    void getAppointmentsByUser_shouldReturnListOfAppointments() {
        // Act
        List<Appointment> result = appointmentService.getAppointmentsByUser(patient.getEmail());

        // Assert
        assertEquals(3, result.size());
        assertEquals(result.get(0).getPatient().getEmail(), patient.getEmail());
    }
}
