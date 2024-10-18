package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.rentalDtos.RentalDTO;
import com.dme.DormitoryProject.entity.Rental;
import com.dme.DormitoryProject.entity.Staff;
import com.dme.DormitoryProject.response.Result;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IRentalService {
    Result getAll();
    Result getById(Long id);
    Result saveRental(RentalDTO rentalDTO);
    Result deleteRental(Long id);
    Result updateRental(Long id, RentalDTO rentalDTO);
    Result afterRental(LocalTime startTime);
}
