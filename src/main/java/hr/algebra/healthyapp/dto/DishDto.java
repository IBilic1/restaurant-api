package hr.algebra.healthyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishDto {

    private String id;

    private String name;

    private List<IngredientDto> ingredients;

    private RestaurantDto restaurant;
}
