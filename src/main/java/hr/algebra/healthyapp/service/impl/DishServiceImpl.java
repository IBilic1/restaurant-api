package hr.algebra.healthyapp.service.impl;

import hr.algebra.healthyapp.exception.EntityDoesNotExistsException;
import hr.algebra.healthyapp.model.Dish;
import hr.algebra.healthyapp.model.Ingredient;
import hr.algebra.healthyapp.model.Restaurant;
import hr.algebra.healthyapp.repository.DishRepository;
import hr.algebra.healthyapp.repository.IngredientRepository;
import hr.algebra.healthyapp.repository.RestaurantRepository;
import hr.algebra.healthyapp.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    private RestaurantRepository restaurantRepository;

    private IngredientRepository ingredientRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository, RestaurantRepository restaurantRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Dish saveDish(Dish dish) {
        Restaurant restaurant = restaurantRepository.findById(dish.getRestaurant().getId()).orElseThrow(EntityDoesNotExistsException::new);
        List<Ingredient> ingredients = dish.getIngredients().stream()
                .map(ingredient -> ingredientRepository.findById(ingredient.getId()).orElseThrow(EntityDoesNotExistsException::new))
                .collect(Collectors.toList());

        dish.setRestaurant(restaurant);
        dish.setIngredients(ingredients);
        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(String dish) {
        dishRepository.deleteById(dish);
    }

    @Override
    public Optional<Dish> getDish(String id) {
        return dishRepository.findById(id);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }
}
