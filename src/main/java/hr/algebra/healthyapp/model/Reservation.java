package hr.algebra.healthyapp.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Reservation {

    private String id;

    private User reservedBy;

    private LocalDateTime reservationTime;

    private Desk desk;
}
