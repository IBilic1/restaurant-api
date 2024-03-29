package hr.algebra.healthyapp.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class Order {

    private String id;

    private User orderBy;

    private List<Dish> dishes;

    private LocalDateTime orderTime;
}
