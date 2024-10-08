package com.dme.DormitoryProject.dtos.managerDtos;

import com.dme.DormitoryProject.entity.Manager;

public class ManagerMapper {
    public static ManagerDTO toDTO(Manager manager){
        ManagerDTO managerDTO = new ManagerDTO();

        managerDTO.setId(manager.getId());
        managerDTO.setMail(manager.getMail());
        managerDTO.setName(manager.getName());
        managerDTO.setPhoneNumber(manager.getPhoneNumber());
        managerDTO.setSalary(manager.getSalary());
        managerDTO.setSurName(manager.getSurName());
        managerDTO.setTitle(manager.getTitle());


        return managerDTO;
    }

    public static Manager toEntitiy(ManagerDTO managerDTO){
        Manager manager = new Manager();

        manager.setMail(managerDTO.getMail());
        manager.setName(managerDTO.getName());
        manager.setPhoneNumber(managerDTO.getPhoneNumber());
        manager.setSalary(managerDTO.getSalary());
        manager.setSurName(managerDTO.getSurName());
        manager.setTitle(managerDTO.getTitle());

        return  manager;
    }
}
