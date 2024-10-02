package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface IRentalService {
    List<RentalDTO> getAll();
    Optional<RentalDTO> getById(Long id);
    Rental saveRental(RentalDTO rentalDTO);
    Rental deleteRental(Long id);
    Rental updateRental(Long id, RentalDTO rentalDTO);
}
