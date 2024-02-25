package hr.algebra.healthyapp.dto;

import hr.algebra.healthyapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private Long id;

    private String name;

    private User owner;
}
