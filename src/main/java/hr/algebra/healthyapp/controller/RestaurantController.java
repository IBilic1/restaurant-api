package hr.algebra.healthyapp.controller;

import hr.algebra.healthyapp.dto.RestaurantDto;
import hr.algebra.healthyapp.mapper.RestaurantMapper;
import hr.algebra.healthyapp.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
@AllArgsConstructor
@Secured({"USER", "ADMIN"})
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class RestaurantController {

    private RestaurantService restaurantService;

    private RestaurantMapper restaurantMapper;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByOwner(Principal principal) {
        return ResponseEntity.ok(restaurantMapper.destinationToSource(
                restaurantService.getRestaurantsByOwner(principal.getName())));
    }

    @PostMapping
    @Secured("ADMIN")
    public ResponseEntity<Void> createRestaurant(@RequestBody RestaurantDto Restaurant, Principal principal) {
        restaurantService.saveRestaurant(restaurantMapper.destinationToSource(Restaurant), principal.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Secured("ADMIN")
    public ResponseEntity<Void> updateRestaurant(@RequestBody RestaurantDto Restaurant, Principal principal) {
        restaurantService.saveRestaurant(restaurantMapper.destinationToSource(Restaurant), principal.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{restaurantId}")
    @Secured("ADMIN")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.ok().build();
    }
}
