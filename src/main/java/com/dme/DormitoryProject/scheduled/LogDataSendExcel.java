package com.dme.DormitoryProject.scheduled;

import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentDTO;
import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentMapper;
import com.dme.DormitoryProject.dtos.lgoDtos.LgoDTO;
import com.dme.DormitoryProject.dtos.lgoDtos.LgoMapper;
import com.dme.DormitoryProject.entity.Department;
import com.dme.DormitoryProject.entity.Lgo;
import com.dme.DormitoryProject.repository.ILgoDao;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LogDataSendExcel {

    private ILgoDao lgoDao;

    public LogDataSendExcel(ILgoDao lgoDao){
        this.lgoDao=lgoDao;
    }

    public List<LgoDTO> entityToDtoList(List<Lgo> lgoList){
        List<LgoDTO> lgoDTOS = new ArrayList<>();

        for (Lgo lgo : lgoList) {
            LgoDTO dto = LgoMapper.toDTO(lgo);
            lgoDTOS.add(dto);
        }
        return lgoDTOS;
    }

    @Scheduled(cron = "0 10 11 * * ?")
    public void exportLogToExcel(){
        List<Lgo> lgoList = lgoDao.findAll();
        List<LgoDTO> lgoDTOList = entityToDtoList(lgoList);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("logs");

        Row headerRow = sheet.createRow(0);
        String[] headers = {"Id","Description","Message"};

        for (int i = 0 ; i < headers.length ; i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int rowNum = 1;

        for (LgoDTO lgoDTO : lgoDTOList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(lgoDTO.getId());
            row.createCell(2).setCellValue(lgoDTO.getLogLevelDescription());
            row.createCell(3).setCellValue(lgoDTO.getMessage());
        }
        try (FileOutputStream fileOut = new FileOutputStream("logs.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Bellekteki kaynakları serbest bırak
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
