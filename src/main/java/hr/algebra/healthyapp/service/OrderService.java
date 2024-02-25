package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getAllOrders();

    Order saveOrder(Order order, String username);

    Optional<Order> getOrder(String id);

    void deleteOrder(String orderId);
}
