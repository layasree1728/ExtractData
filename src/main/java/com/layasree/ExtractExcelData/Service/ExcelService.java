package com.layasree.ExtractExcelData.Service;
import com.layasree.ExtractExcelData.Entity.EmployeeData;
import com.layasree.ExtractExcelData.Exception.CorruptedFileException;
import com.layasree.ExtractExcelData.Exception.EmptyFileException;
import com.layasree.ExtractExcelData.Exception.InvalidFileFormatException;
import com.layasree.ExtractExcelData.Repository.ExcelDataRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExcelService {

    private final ExcelDataRepository excelDataRepository;

    public ExcelService(ExcelDataRepository excelDataRepository) {
        this.excelDataRepository = excelDataRepository;
    }

    public String processExcelFile(MultipartFile file) throws IOException, InvalidFileFormatException, EmptyFileException, CorruptedFileException {
        // AC2: Validate file format
        if (!file.getOriginalFilename().endsWith(".xlsx")) {
            throw new InvalidFileFormatException("Uploaded file is not in Excel format (.xlsx required).");
        }

        List<EmployeeData> employees = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            // AC3: Check if file is empty
            if (sheet.getPhysicalNumberOfRows() == 0) {
                throw new EmptyFileException("Uploaded Excel file is empty.");
            }
            int rowIndex = 1;
            boolean isFirstRow = true;
            for (Row row : sheet) {
               /* if (isFirstRow) {
                    isFirstRow = false; // Skip header row
                    continue;
                }*/
                if (rowIndex < 3) {
                    rowIndex++;
                    continue;
                }

                EmployeeData employee = new EmployeeData();

                try {
                    // Populate EmployeeData fields from row cells
                    //employee.setName(row.getCell(0).getStringCellValue());
                    employee.setUid((int) row.getCell(1).getNumericCellValue());
                    employee.setLocation(row.getCell(2).getStringCellValue());
                    employee.setDoj(row.getCell(3).getDateCellValue());
                    employee.setTimeWithEpam(row.getCell(4).getStringCellValue());
                    employee.setTitle(row.getCell(5).getStringCellValue());
                    employee.setStatus(row.getCell(6).getStringCellValue());
                    employee.setProductionCategory(row.getCell(7).getStringCellValue());
                    employee.setJobFunction(row.getCell(8).getStringCellValue());
                    employee.setResourceManager(row.getCell(9).getStringCellValue());
                    employee.setPgm(row.getCell(10).getStringCellValue());
                    employee.setProjectCode(row.getCell(11).getStringCellValue());
                    employee.setJfLevel(row.getCell(12).getStringCellValue());
                    employee.setCompetencyPractice(row.getCell(13).getStringCellValue());
                    employee.setPrimarySkill(row.getCell(14).getStringCellValue());
                    employee.setNicheSkills(row.getCell(15).getStringCellValue());
                    employee.setNicheSkillYesNo(row.getCell(16).getStringCellValue());
                    employee.setTalentProfile2020(row.getCell(17).getStringCellValue());
                    employee.setDeliveryFeedbackTtScore(row.getCell(18).getNumericCellValue());
                    employee.setPracticeRating(row.getCell(19).getNumericCellValue());
                    employee.setStrategicOrientation(row.getCell(20).getNumericCellValue());
                    employee.setInfluence(row.getCell(21).getNumericCellValue());
                    employee.setResultOrientation(row.getCell(22).getNumericCellValue());
                    employee.setCommunication(row.getCell(23).getNumericCellValue());
                    employee.setDecisionMaking(row.getCell(24).getNumericCellValue());
                    employee.setAskForMore(row.getCell(25).getNumericCellValue());
                    employee.setAgility(row.getCell(26).getNumericCellValue());
                    employee.setVisibility(row.getCell(27).getNumericCellValue());
                    employee.setFeedback(row.getCell(28).getNumericCellValue());
                    employee.setInitiativeNewAndDifferent(row.getCell(29).getNumericCellValue());
                    employee.setDevelopingOrgCapabilities(row.getCell(30).getNumericCellValue());
                    employee.setStay(row.getCell(31).getNumericCellValue());
                    employee.setPersonalConnect(row.getCell(32).getNumericCellValue());
                    employee.setExcellence(row.getCell(33).getNumericCellValue());
                    employee.setSay(row.getCell(34).getNumericCellValue());
                    employee.setContributionEngXCulture(row.getCell(35).getStringCellValue());
                    employee.setContributionExtraMiles(row.getCell(36).getStringCellValue());
                    employee.setCultureScore(row.getCell(37).getNumericCellValue());
                    employee.setOverallWeightedScoreForMerit(row.getCell(38).getNumericCellValue());
                    employee.setRanking(row.getCell(39).getStringCellValue());
                    employee.setPercentile(row.getCell(40).getNumericCellValue());
                    employee.setHrbpMapping(row.getCell(41).getStringCellValue());
                    employee.setDh(row.getCell(42).getStringCellValue());
                } catch (Exception e) {
                    throw new CorruptedFileException("Error processing row " + row.getRowNum() + ": possible data corruption.");
                }

                employees.add(employee);
            }
        } catch (IOException e) {
            // AC4: Handle corrupted file
            throw new CorruptedFileException("Uploaded file is corrupted or cannot be read.");
        }

        excelDataRepository.saveAll(employees);
        return "File processed and data stored in the database successfully!";
    }
}