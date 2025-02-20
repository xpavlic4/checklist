package org.springframework.samples.petclinic.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExcelExportService {

    public byte[] generateExcel(List<Map<String, Object>> data) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            if (data.isEmpty()) {
                return new byte[0]; // Return empty file if no data
            }

            // Create header row
            Row headerRow = sheet.createRow(0);
            List<String> keys = data.get(0).keySet().stream().toList();
            for (int i = 0; i < keys.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(keys.get(i));
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            // Populate data rows
            for (int rowIdx = 0; rowIdx < data.size(); rowIdx++) {
                Row row = sheet.createRow(rowIdx + 1);
                Map<String, Object> rowData = data.get(rowIdx);
                for (int colIdx = 0; colIdx < keys.size(); colIdx++) {
                    row.createCell(colIdx).setCellValue(rowData.get(keys.get(colIdx)).toString());
                }
            }

            // Auto-size columns
            for (int i = 0; i < keys.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to byte array
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}
