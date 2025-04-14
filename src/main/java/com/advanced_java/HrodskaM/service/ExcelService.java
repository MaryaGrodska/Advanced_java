package com.advanced_java.HrodskaM.service;

import com.advanced_java.HrodskaM.dto.CryptoCurrencyQuote;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExcelService {

    public void exportToExcel(List<CryptoCurrencyQuote> quotes, HttpServletResponse response) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Quotes");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Currency");
        header.createCell(1).setCellValue("Price (USD)");
        header.createCell(2).setCellValue("Timestamp");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int rowIdx = 1;
        for (CryptoCurrencyQuote quote : quotes) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(quote.getCurrency());
            row.createCell(1).setCellValue(quote.getPrice());
            row.createCell(2).setCellValue(quote.getTimestamp().format(formatter));
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=quotes.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
