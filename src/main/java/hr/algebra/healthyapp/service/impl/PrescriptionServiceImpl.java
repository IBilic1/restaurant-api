package hr.algebra.healthyapp.service.impl;

import hr.algebra.healthyapp.exception.EntityDoesNotExistsException;
import hr.algebra.healthyapp.model.Prescription;
import hr.algebra.healthyapp.model.User;
import hr.algebra.healthyapp.repository.OrderRepository;
import hr.algebra.healthyapp.repository.PrescriptionRepository;
import hr.algebra.healthyapp.repository.UserRepository;
import hr.algebra.healthyapp.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private PrescriptionRepository prescriptionRepository;

    private UserRepository userRepository;

    private OrderRepository orderRepository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Prescription savePrescription(Prescription prescription, String name) {
        String patientEmail = prescription.getPatient().getEmail();
        String doctorEmail = prescription.getDoctor().getEmail();
        Optional<User> oPatient = userRepository.findByEmail(patientEmail);
        Optional<User> oDoctor = userRepository.findByEmail(doctorEmail);

        if (oDoctor.isEmpty()) {
            throw new EntityDoesNotExistsException("Doctor with username %s does not exists", doctorEmail);
        }
        if (oPatient.isEmpty()) {
            throw new EntityDoesNotExistsException("Patient with username %s does not exists", patientEmail);
        }
        User doctor = oDoctor.get();
        User patient = oPatient.get();
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        return prescriptionRepository.save(prescription);
    }

    @Override
    public void deletePrescription(Long prescription) {
        prescriptionRepository.deleteById(prescription);
    }

    @Override
    public Optional<Prescription> getPrescription(Long id) {
        return prescriptionRepository.findById(id);
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctor(String username) {
        User doctor = userRepository.findByEmail(username).orElseThrow(()->
                new EntityDoesNotExistsException("Doctor with username %s does not exists", username));
        return prescriptionRepository.findByDoctorId(doctor.getId());
    }

    @Override
    public List<Prescription> getPrescriptionsByUser(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(()->
                new EntityDoesNotExistsException("Patient with username %s does not exists", username));
        return prescriptionRepository.findByPatientId(user.getId());
    }
}
