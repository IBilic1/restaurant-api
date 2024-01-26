package hr.algebra.healthyapp.service.impl;

import hr.algebra.healthyapp.model.Medicine;
import hr.algebra.healthyapp.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Override
    public Double getStock(Medicine medicine) {
        return  Math.floor(Math.random() * 10) + 1;
    }
}
