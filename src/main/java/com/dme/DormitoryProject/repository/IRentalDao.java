package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IRentalDao extends JpaRepository<Rental,Long> {
    Rental findRentalById(Long id);
    List<Rental> findBySportAreaIdAndRentalDate(Long id, LocalDate date);
    List<Rental> findByStartTimeAfter(LocalTime startTime);
}
