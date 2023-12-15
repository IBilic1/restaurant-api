package hr.algebra.healthyapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {

    private Long id;

    private String name;

    private String description;

    private Long manufacturerId;
}
