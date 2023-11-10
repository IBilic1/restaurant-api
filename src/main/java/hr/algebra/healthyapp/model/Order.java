package hr.algebra.healthyapp.model;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
public class Order {

//    @Id
    private Long id;

    private String description;

    private Double amount;

    private Double doseGap;
//
//    @ManyToOne
//    @JoinColumn(name = "id")
    private Medicine medicine;
//
//    @ManyToOne
    private Prescription prescription;
}
