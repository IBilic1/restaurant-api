package hr.algebra.healthyapp.dto;

import hr.algebra.healthyapp.model.Dish;
import hr.algebra.healthyapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String id;

    private User orderBy;

    private List<Dish> dishes;

    private LocalDateTime orderTime;
}
