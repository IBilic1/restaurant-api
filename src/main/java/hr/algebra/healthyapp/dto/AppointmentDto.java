package hr.algebra.healthyapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Long id;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String address;

    private UserDto doctor;

    private UserDto patient;
}
