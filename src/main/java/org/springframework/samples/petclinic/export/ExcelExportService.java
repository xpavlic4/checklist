package org.springframework.samples.petclinic.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {

	public byte[] generateExcel(List<List<String>> data) throws IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Case");

			if (data.isEmpty()) {
				return new byte[0]; // Return empty file if no data
			}

			// Create header row
			Row headerRow = sheet.createRow(0);
			List<String> keys = data.get(0);
			for (int i = 0; i < keys.size(); i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(keys.get(i));
				cell.setCellStyle(createHeaderCellStyle(workbook));
			}

			// Populate data rows
			for (int rowIdx = 1; rowIdx < data.size(); rowIdx++) {
				Row row = sheet.createRow(rowIdx);
				List<String> rowData = data.get(rowIdx);
				for (int colIdx = 0; colIdx < keys.size(); colIdx++) {
					row.createCell(colIdx).setCellValue(rowData.get(colIdx));
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
