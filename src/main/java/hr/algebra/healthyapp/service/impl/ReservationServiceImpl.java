package hr.algebra.healthyapp.service.impl;

import hr.algebra.healthyapp.exception.EntityDoesNotExistsException;
import hr.algebra.healthyapp.model.Desk;
import hr.algebra.healthyapp.model.Reservation;
import hr.algebra.healthyapp.model.User;
import hr.algebra.healthyapp.repository.DeskRepository;
import hr.algebra.healthyapp.repository.ReservationRepository;
import hr.algebra.healthyapp.repository.UserRepository;
import hr.algebra.healthyapp.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    private DeskRepository deskRepository;

    private UserRepository userRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, DeskRepository deskRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.deskRepository = deskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Reservation> getAllReservations(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new EntityDoesNotExistsException("Owner with username %s does not exists", username));
        return reservationRepository.findAllByReservedBy_Id(user.getId());
    }

    @Override
    public Reservation reservation(Reservation reservation, String username) {
        User user = userRepository.findByEmail(username).orElseThrow(EntityDoesNotExistsException::new);
        Desk desk = deskRepository.findById(reservation.getDesk().getId()).orElseThrow(EntityDoesNotExistsException::new);
        reservation.setDesk(desk);
        reservation.setReservedBy(user);

        List<Reservation> reservationsByDesk_id = reservationRepository.findReservationsByDesk_Id(desk.getId());
        List<Reservation> reservationsWithSameReservationTime = reservationsByDesk_id.stream().filter(r ->
                r.getReservationTime().toLocalDate().equals(reservation.getReservationTime().toLocalDate())
                && (r.getReservationTime().getHour() == reservation.getReservationTime().getHour()))
                .collect(Collectors.toList());
        if (reservationsWithSameReservationTime.size() > 0){
            throw new RuntimeException("There is already reservation");
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> getReservation(String id) {
       return reservationRepository.findById(id);
    }

    @Override
    public void deleteReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
