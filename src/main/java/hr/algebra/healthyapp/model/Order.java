package hr.algebra.healthyapp.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "order_")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double amount;

    private Double doseGap;

    @ManyToOne
    @JoinColumn(name = "medicineId")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "prescriptionId", insertable = false, updatable = false)
    private Prescription prescription;
}
