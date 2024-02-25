package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.model.Desk;

import java.util.List;
import java.util.Optional;

public interface DeskService {

    List<Desk> getAllDesks();

    Desk saveDesk(Desk desk);

    void deleteDesk(String deskId);

    Optional<Desk> getDesk(String id);
}
