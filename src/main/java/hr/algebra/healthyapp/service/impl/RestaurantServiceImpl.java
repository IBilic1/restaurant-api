package hr.algebra.healthyapp.service.impl;

import hr.algebra.healthyapp.exception.EntityDoesNotExistsException;
import hr.algebra.healthyapp.model.Restaurant;
import hr.algebra.healthyapp.model.User;
import hr.algebra.healthyapp.repository.RestaurantRepository;
import hr.algebra.healthyapp.repository.UserRepository;
import hr.algebra.healthyapp.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    private UserRepository userRepository;

    @Override
    public void saveRestaurant(Restaurant restaurant, String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new EntityDoesNotExistsException("Owner with username %s does not exists", username));
        restaurant.setOwner(user);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(String restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public Optional<Restaurant> getRestaurant(String id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurant> getRestaurantsByOwner(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new EntityDoesNotExistsException("Owner with username %s does not exists", username));
        return restaurantRepository.findAllByOwnerId(user.getId());
    }
}
