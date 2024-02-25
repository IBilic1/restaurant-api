package hr.algebra.healthyapp.repository;

import hr.algebra.healthyapp.model.Desk;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeskRepository extends MongoRepository<Desk, String> {

}
