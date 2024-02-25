package hr.algebra.healthyapp.service.impl;

import hr.algebra.healthyapp.model.Ingredient;
import hr.algebra.healthyapp.repository.IngredientRepository;
import hr.algebra.healthyapp.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(String ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

    @Override
    public Optional<Ingredient> getIngredient(String id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
