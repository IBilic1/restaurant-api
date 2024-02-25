package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    Ingredient saveIngredient(Ingredient ingredient);

    void deleteIngredient(String ingredientId);

    Optional<Ingredient> getIngredient(String id);

    List<Ingredient> getAllIngredients();
}
