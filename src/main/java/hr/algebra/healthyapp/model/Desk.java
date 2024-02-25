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
public class Desk {

    private String id;

    private Restaurant restaurant;

    private Integer size;
}
