package hr.algebra.healthyapp.service.impl;

import hr.algebra.healthyapp.model.Desk;
import hr.algebra.healthyapp.repository.DeskRepository;
import hr.algebra.healthyapp.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeskServiceImpl implements DeskService {

    private DeskRepository deskRepository;

    @Autowired
    public DeskServiceImpl(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    @Override
    public List<Desk> getAllDesks() {
        return deskRepository.findAll();
    }

    @Override
    public Desk saveDesk(Desk desk) {
        return deskRepository.save(desk);
    }

    @Override
    public void deleteDesk(String deskId) {
        deskRepository.deleteById(deskId);
    }

    @Override
    public Optional<Desk> getDesk(String id) {
        return deskRepository.findById(id);
    }
}
