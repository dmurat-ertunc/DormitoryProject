package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface IStaffService {
    List<StaffDTO> getAll();
    Optional<StaffDTO> getById(Long id);
    Staff saveStaff(StaffDTO staffDTO);
    Staff deleteStaff(Long id);
    Staff updateStaff(Long id, StaffDTO staffDTO);
}
