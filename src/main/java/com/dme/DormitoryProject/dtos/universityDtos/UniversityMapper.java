package com.dme.DormitoryProject.dtos.universityDtos;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.entity.University;
import com.dme.DormitoryProject.repository.IStudentDao;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UniversityMapper {
    public static UniversityDTO toDTO(University university) {
        UniversityDTO dto = new UniversityDTO();
        dto.setId(university.getId());
        dto.setName(university.getName());
        dto.setMail(university.getmail());
        dto.setPhoneNumber(university.getphoneNumber());
        dto.setCity(university.getcity());

        Set<Long> studentIds = university.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toSet());
        dto.setStudentIds(studentIds);

        Set<String> studentName = university.getStudents().stream()
                .map(Student::getName)
                .collect(Collectors.toSet());
        dto.setStudentName(studentName);

        return dto;
    }

    public static University toEntity(UniversityDTO dto, IStudentDao studentDao) {
        University university = new University();
        university.setName(dto.getName());
        university.setmail(dto.getMail());
        university.setphoneNumber(dto.getPhoneNumber());
        university.setcity(dto.getCity());

        Set<Student> students = dto.getStudentIds().stream()
                .map(studentDao::findById)  // Öğrencileri repository'den bul
                .filter(Optional::isPresent)       // Sadece var olanları al
                .map(Optional::get)                // Optional'ı aç ve Student'ı al
                .collect(Collectors.toSet());

        university.setStudents(students); // Üniversiteye öğrenci listesini set et

        return university;
    }
}
