package hr.algebra.healthyapp.controller;

import hr.algebra.healthyapp.dto.IngredientDto;
import hr.algebra.healthyapp.mapper.IngredientMapper;
import hr.algebra.healthyapp.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private IngredientService ingredientService;

    private IngredientMapper ingredientMapper;

    @Autowired
    public IngredientController(IngredientService ingredientService, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
    }

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredientes() {
        return ResponseEntity.ok(ingredientMapper.destinationToSource(ingredientService.getAllIngredients()));
    }

    @PostMapping
    @Secured("ADMIN")
    public ResponseEntity<Void> createIngredient(@RequestBody IngredientDto ingredient) {
        ingredientService.saveIngredient(ingredientMapper.destinationToSource(ingredient));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Secured("ADMIN")
    public ResponseEntity<Void> updateIngredient(@RequestBody IngredientDto ingredient) {
        ingredientService.saveIngredient(ingredientMapper.destinationToSource(ingredient));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{ingredientId}")
    @Secured("ADMIN")
    public ResponseEntity<Void> deleteIngredient(@PathVariable String ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
        return ResponseEntity.ok().build();
    }
}
