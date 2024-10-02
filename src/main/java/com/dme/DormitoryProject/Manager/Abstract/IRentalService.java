package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface IRentalService {
    List<Rental> getAll();
    Optional<Rental> getById(Long id);
    Rental saveRental(Rental rental);
    Rental deleteRental(Long id);
    Rental updateRental(Long id, Rental rental);
}
