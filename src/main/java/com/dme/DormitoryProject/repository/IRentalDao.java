package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentalDao extends JpaRepository<Rental,Long> {
    Rental findRentalById(Long id);
}
