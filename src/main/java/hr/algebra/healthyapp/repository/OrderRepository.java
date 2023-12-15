package hr.algebra.healthyapp.repository;

import hr.algebra.healthyapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
