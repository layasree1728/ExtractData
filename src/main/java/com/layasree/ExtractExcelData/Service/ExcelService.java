package com.layasree.ExtractExcelData.Service;
import com.layasree.ExtractExcelData.Entity.ExcelData;
import com.layasree.ExtractExcelData.Repository.ExcelDataRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.*;

@Service
public class ExcelService {

    @Autowired
    private ExcelDataRepository excelDataRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public void saveExcelData(MultipartFile file) throws Exception {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<ExcelData> dataList = new ArrayList<>();

            // Read header row to get column names
            Row headerRow = sheet.getRow(0);
            int columnCount = headerRow.getPhysicalNumberOfCells();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                Map<String, Object> rowData = new LinkedHashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = headerRow.getCell(i).getStringCellValue();
                    Cell cell = row.getCell(i);
                    Object cellValue = getCellValue(cell);
                    rowData.put(columnName, cellValue);
                }

                ExcelData excelData = new ExcelData();
                excelData.setRowDataJson(objectMapper.writeValueAsString(rowData));
                dataList.add(excelData);
            }

            excelDataRepository.saveAll(dataList);
        }
    }

    private Object getCellValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN: return cell.getBooleanCellValue();
            case FORMULA: return cell.getCellFormula();
            default: return null;
        }
    }
}
