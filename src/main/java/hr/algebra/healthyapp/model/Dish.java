package hr.algebra.healthyapp.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Dish {

    private String id;

    private String name;

    private List<Ingredient> ingredients;

    private Restaurant restaurant;
}
