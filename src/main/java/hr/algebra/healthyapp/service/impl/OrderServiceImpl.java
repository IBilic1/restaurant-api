package hr.algebra.healthyapp.service.impl;

import hr.algebra.healthyapp.exception.EntityDoesNotExistsException;
import hr.algebra.healthyapp.model.Dish;
import hr.algebra.healthyapp.model.Order;
import hr.algebra.healthyapp.model.User;
import hr.algebra.healthyapp.repository.DishRepository;
import hr.algebra.healthyapp.repository.OrderRepository;
import hr.algebra.healthyapp.repository.UserRepository;
import hr.algebra.healthyapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private UserRepository userRepository;

    private DishRepository dishRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, DishRepository dishRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Order> getAllOrders(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new EntityDoesNotExistsException("Owner with username %s does not exists", username));
        return orderRepository.findAllByOrderBy_Id(username);
    }

    @Override
    public Order saveOrder(Order order, String username) {
        User user = userRepository.findByEmail(username).orElseThrow(RuntimeException::new);
        List<Dish> dishes = order.getDishes().stream().map(dish -> dishRepository.findById(dish.getId()).orElseThrow(EntityDoesNotExistsException::new))
                .collect(Collectors.toList());

        order.setOrderBy(user);
        order.setDishes(dishes);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrder(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }
}
