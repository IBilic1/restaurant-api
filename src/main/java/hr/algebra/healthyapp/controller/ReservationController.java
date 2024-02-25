package hr.algebra.healthyapp.controller;

import hr.algebra.healthyapp.dto.ReservationDto;
import hr.algebra.healthyapp.mapper.ReservationMapper;
import hr.algebra.healthyapp.model.Reservation;
import hr.algebra.healthyapp.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private ReservationService reservationService;

    private ReservationMapper reservationMapper;

    @Autowired
    public ReservationController(ReservationService deskService, ReservationMapper reservationMapper) {
        this.reservationService = deskService;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getReservations() {
        return ResponseEntity.ok(reservationMapper.destinationToSource(reservationService.getAllReservations()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable String id) {
        return ResponseEntity.of(reservationService.getReservation(id).map(reservationMapper::sourceToDestination));
    }

    @PutMapping
    public ResponseEntity<Void> reservation(@RequestBody Reservation reservation, Principal principal) {
        reservationService.reservation(reservation, principal.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok().build();
    }
}
