package hr.algebra.healthyapp.controller;

import hr.algebra.healthyapp.dto.OrderDto;
import hr.algebra.healthyapp.mapper.OrderMapper;
import hr.algebra.healthyapp.model.Order;
import hr.algebra.healthyapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    private OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        return ResponseEntity.ok(orderMapper.destinationToSource(orderService.getAllOrders()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String id) {
        return ResponseEntity.of(orderService.getOrder(id).map(orderMapper::sourceToDestination));
    }

    @PutMapping
    public ResponseEntity<Void> saveOrder(@RequestBody Order order, Principal principal) {
        orderService.saveOrder(order, principal.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
