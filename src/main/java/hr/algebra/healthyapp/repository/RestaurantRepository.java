package hr.algebra.healthyapp.repository;

import hr.algebra.healthyapp.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    List<Restaurant> findAllByOwnerId(String id);
}
