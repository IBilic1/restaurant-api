package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> getAllReservations();

    Reservation reservation(Reservation reservation, String username);

    Optional<Reservation> getReservation(String id);

    void deleteReservation(String reservationId);
}
