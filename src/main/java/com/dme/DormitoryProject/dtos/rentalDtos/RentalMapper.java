package com.dme.DormitoryProject.dtos.rentalDtos;

import com.dme.DormitoryProject.entity.Rental;

public class RentalMapper {
    public static RentalDTO toDTO(Rental rental){
        RentalDTO rentalDTO = new RentalDTO();

        rentalDTO.setEndTime(rental.getEndTime());
        rentalDTO.setRentalDate(rental.getRentalDate());
        rentalDTO.setStartTime(rental.getStartTime());
        rentalDTO.setSportAreaId(rental.getSportArea().getId());
        rentalDTO.setStudentId(rental.getStudent().getId());
        rentalDTO.setStudentBirthDate(rental.getStudent().getBirthDate());
        rentalDTO.setStudentMail(rental.getStudent().getMail());
        rentalDTO.setStudentName(rental.getStudent().getName());
        rentalDTO.setStudentSurName(rental.getStudent().getSurName());
        rentalDTO.setStudenTcNo(rental.getStudent().getTcNo());
        rentalDTO.setStudentVerify(rental.getStudent().getVerify());

        return rentalDTO;
    }

    public static Rental toEntity(RentalDTO rentalDTO){
        Rental rental = new Rental();

        rental.setEndTime(rentalDTO.getEndTime());
        rental.setRentalDate(rentalDTO.getRentalDate());
        rental.setStartTime(rentalDTO.getStartTime());
        rental.getSportArea().setId(rentalDTO.getSportAreaId());
        rental.getStudent().setId(rentalDTO.getStudentId());
        rental.getStudent().setBirthDate(rentalDTO.getStudentBirthDate());
        rental.getStudent().setMail(rentalDTO.getStudentMail());
        rental.getStudent().setName(rentalDTO.getStudentName());
        rental.getStudent().setSurName(rentalDTO.getStudentSurName());
        rental.getStudent().setTcNo(rentalDTO.getStudentTcNo());
        rental.getStudent().setVerify(rentalDTO.isStudentVerify());

        return rental;
    }
}
