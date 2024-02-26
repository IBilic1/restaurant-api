package hr.algebra.healthyapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeskDto {

    private String id;

    private RestaurantDto restaurant;

    private Integer size;
}
