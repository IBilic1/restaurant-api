package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    void saveRestaurant(Restaurant restaurant, String name);

    void deleteRestaurant( String RestaurantId);

    Optional<Restaurant> getRestaurant(String id);

    List<Restaurant> getRestaurantsByOwner(String username);
}
