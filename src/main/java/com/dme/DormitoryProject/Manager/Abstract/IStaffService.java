package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.staffDtos.StaffDTO;
import com.dme.DormitoryProject.entity.Staff;
import com.dme.DormitoryProject.response.Result;

import java.util.List;
import java.util.Optional;

public interface IStaffService {
    Result getAll();
    Result getById(Long id);
    Result saveStaff(StaffDTO staffDTO);
    Result deleteStaff(Long id);
    Result updateStaff(Long id, StaffDTO staffDTO);
}
