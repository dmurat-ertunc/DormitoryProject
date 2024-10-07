package com.dme.DormitoryProject.dtos.studentDtos;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.entity.University;
import com.dme.DormitoryProject.repository.IUniversityDao;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentMapper {
    // Entity to DTO dönüşümü
    public static StudentDTO toDto(Student student) {
        if (student == null) {
            return null;
        }

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setSurName(student.getSurName());
        dto.setTcNo(student.getTcNo());
        dto.setMail(student.getMail());
        dto.setBirthDate(student.getBirthDate());
        dto.setVerify(student.isVerify());

        // Üniversite ID'lerini setlemek için
        Set<Long> universityIds = student.getUniversity().stream()
                .map(University::getId)
                .collect(Collectors.toSet());
        dto.setUniversityIds(universityIds);

        Set<String> univertsiyName = student.getUniversity().stream()
                .map(University::getName)
                .collect(Collectors.toSet());
        dto.setUniversityName(univertsiyName);



        return dto;
    }

    // DTO to Entity dönüşümü
    public static Student toEntity(StudentDTO dto, IUniversityDao universityDao) {
        if (dto == null) {
            return null;
        }

        Student student = new Student();
        student.setName(dto.getName());
        student.setSurName(dto.getSurName());
        student.setTcNo(dto.getTcNo());
        student.setMail(dto.getMail());
        student.setBirthDate(dto.getBirthDate());
        student.setVerify(dto.isVerify());

        // Üniversite ID'lerini setlemek için
        Set<University> universities = dto.getUniversityIds().stream()
                .map(universityDao::findById)  // Öğrencileri repository'den bul
                .filter(Optional::isPresent)       // Sadece var olanları al
                .map(Optional::get)                // Optional'ı aç ve Student'ı al
                .collect(Collectors.toSet());

        student.setUniversity(universities);

        return student;
    }
}
