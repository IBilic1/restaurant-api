package hr.algebra.healthyapp.service;

import hr.algebra.healthyapp.model.Medicine;

public interface SupplierService {

    Double getStock(Medicine medicine);
}
