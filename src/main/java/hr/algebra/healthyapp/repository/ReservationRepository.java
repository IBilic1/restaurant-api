package hr.algebra.healthyapp.repository;

import hr.algebra.healthyapp.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    List<Reservation> findReservationsByDesk_Id(String deskId);
}
