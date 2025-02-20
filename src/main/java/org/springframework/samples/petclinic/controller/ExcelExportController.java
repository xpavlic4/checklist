package org.springframework.samples.petclinic.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.samples.petclinic.export.ExcelExportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/export")
public class ExcelExportController {

    private final ExcelExportService excelExportService;

    public ExcelExportController(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    @GetMapping("/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");

        List<Map<String, Object>> data = List.of(
            Map.of("ID", "p.A1.P1.Z1", "Argument", "A1", "predikát (P)", "P1: Chodec měl vhodnou obuv",
				"inference", " ,protože/because", "premisa (Z)", "Z1: podrážka boty měla hrubý vzorek. ")
        );

        byte[] excelData = excelExportService.generateExcel(data);
        response.getOutputStream().write(excelData);
    }
}
