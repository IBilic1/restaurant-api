package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {

    Dish saveDish(Dish dish);

    void deleteDish(String dish);

    Optional<Dish> getDish(String id);

    List<Dish> getAllDishes();
}
