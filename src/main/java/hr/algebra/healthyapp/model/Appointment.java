package hr.algebra.healthyapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String address;

    @ManyToOne(cascade = {CascadeType.MERGE })
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne(cascade = {CascadeType.MERGE })
    @JoinColumn(name = "patient_id")
    private User patient;
}
