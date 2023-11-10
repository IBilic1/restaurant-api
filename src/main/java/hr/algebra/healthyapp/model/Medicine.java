package hr.algebra.healthyapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
public class Medicine {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String manufacturer;
}
