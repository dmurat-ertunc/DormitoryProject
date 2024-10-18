package com.dme.DormitoryProject.scheduled;

import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentDTO;
import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentMapper;
import com.dme.DormitoryProject.dtos.lgoDtos.LgoDTO;
import com.dme.DormitoryProject.dtos.lgoDtos.LgoMapper;
import com.dme.DormitoryProject.entity.Department;
import com.dme.DormitoryProject.entity.Lgo;
import com.dme.DormitoryProject.repository.ILgoDao;
import com.dme.DormitoryProject.response.ErrorResult;
import com.dme.DormitoryProject.response.Result;
import com.dme.DormitoryProject.response.SuccessDataResult;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    @Scheduled(cron = "20 06 13 * * ?")
    public Result exportLogToExcel(){
        List<Lgo> lgoList = lgoDao.findAll();
        List<LgoDTO> lgoDTOList = entityToDtoList(lgoList);
        Workbook workbook;
        Sheet sheet;
        File file = new File("logs.xlsx");
        // Var olan dosyayı açma
        try (FileInputStream fis = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fis);
            // "logs" adında bir sayfa var mı kontrol et
            sheet = workbook.getSheet("logs");
            if (sheet == null) {
                // Eğer "logs" sayfası yoksa yeni bir sayfa oluştur
                sheet = workbook.createSheet("logs");
            }
        } catch (FileNotFoundException e) {
            // Dosya bulunamazsa yeni bir workbook oluştur
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("logs");
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Id","Description","Message","Date"};
            for (int i = 0 ; i < headers.length ; i++){
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorResult("Hata",false); // Hata durumunda işlemi durdur
        }
        int rowCount = 0;
        try (FileInputStream fis = new FileInputStream("logs.xlsx");
             Workbook workbook1 = new XSSFWorkbook(fis)) {

            Sheet sheet1 = workbook1.getSheet("logs"); // İlgili sayfayı al
            if (sheet != null) {
                rowCount = sheet.getPhysicalNumberOfRows(); // Fiziksel satır sayısını al
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        for (LgoDTO lgoDTO : lgoDTOList){
            LocalDate localDate = lgoDTO.getDate();  // Tarihin buradan geldiğini varsayıyoruz
        }
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        for (LgoDTO lgoDTO : lgoDTOList) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(lgoDTO.getId());
            row.createCell(1).setCellValue(lgoDTO.getLogLevelDescription());
            row.createCell(2).setCellValue(lgoDTO.getMessage());
            row.createCell(3).setCellValue(lgoDTO.getDate());
            row.getCell(3).setCellStyle(cellStyle);
            System.out.println(lgoDTO.getDate());
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

        System.out.println(file.getAbsoluteFile());
        lgoDao.deleteAll();
        return new SuccessDataResult("Bu URL ile dosyayı indirebilirsiniz",true,file.getAbsoluteFile());
    }
}
