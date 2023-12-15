package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.model.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {

    void savePrescription(Prescription prescription, String name);

    void deletePrescription(Long prescription);

    Optional<Prescription> getPrescription(Long id);

    List<Prescription> getPrescriptionsByDoctor(String username);

    List<Prescription> getPrescriptionsByUser(String username);
}
