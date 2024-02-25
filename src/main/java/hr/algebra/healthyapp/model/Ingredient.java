package hr.algebra.healthyapp.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Ingredient {

    private String id;

    private String name;
}
