package hr.algebra.healthyapp.model;

import java.util.List;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
public class Prescription {


//    @Id
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "id")
    private User patient;
//
//    @OneToMany(mappedBy = "prescription")
    private List<Order> order;
}
