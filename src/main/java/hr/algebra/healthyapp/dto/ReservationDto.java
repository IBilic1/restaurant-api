package hr.algebra.healthyapp.dto;

import hr.algebra.healthyapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long id;

    private User reservedBy;

    private LocalDateTime reservationTime;

    private DeskDto desk;
}
