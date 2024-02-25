package hr.algebra.healthyapp.controller;

import hr.algebra.healthyapp.dto.DishDto;
import hr.algebra.healthyapp.mapper.DishMapper;
import hr.algebra.healthyapp.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishController {

    private DishService dishService;

    private DishMapper dishMapper;

    @Autowired
    public DishController(DishService dishService, DishMapper dishMapper) {
        this.dishService = dishService;
        this.dishMapper = dishMapper;
    }

    @GetMapping
    public ResponseEntity<List<DishDto>> getAllDishes() {
        return ResponseEntity.ok(dishMapper.destinationToSource(dishService.getAllDishes()));
    }

    @PostMapping
    @Secured("ADMIN")
    public ResponseEntity<Void> createDish(@RequestBody DishDto dish) {
        dishService.saveDish(dishMapper.destinationToSource(dish));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Secured("ADMIN")
    public ResponseEntity<Void> updateDish(@RequestBody DishDto dish) {
        dishService.saveDish(dishMapper.destinationToSource(dish));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{dishId}")
    @Secured("ADMIN")
    public ResponseEntity<Void> deleteDish(@PathVariable String dishId) {
        dishService.deleteDish(dishId);
        return ResponseEntity.ok().build();
    }
}
