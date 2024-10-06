package com.dme.DormitoryProject.annotations;

import com.dme.DormitoryProject.entity.Manager;
import com.dme.DormitoryProject.repository.IManagerDao;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;

public final class MailUniqueCheckValidator implements ConstraintValidator<MailUniqueCheck,String>{
    private final IManagerDao managerDao;
    public  MailUniqueCheckValidator(IManagerDao managerDao){
        this.managerDao=managerDao;
    }
    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context){
        List<Manager> managers = managerDao.findAll();
        for (Manager manager : managers){
            if(Objects.equals(manager.getMail(),mail)){
                return false;
            }
        }
        return true;
    }
}
