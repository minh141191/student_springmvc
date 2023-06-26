package com.example.student_jpa.service;

import com.example.student_jpa.model.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudentExcelExporter {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<Student> studentList;

    public StudentExcelExporter(List<Student> studentList) {
        this.studentList = studentList;
        workbook = new XSSFWorkbook();
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Students");
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "Age", style);
        createCell(row, 3, "Point", style);
        createCell(row, 4, "Classes", style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Student student : studentList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, student.getId(), style);
            createCell(row, columnCount++, student.getName(), style);
            createCell(row, columnCount++, student.getAge(), style);
            createCell(row, columnCount++, student.getPoint(), style);
            createCell(row, columnCount, student.getClasses().getName(), style);
        }
    }

    public void export(HttpServletResponse response) {
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            writeHeaderLine();
            writeDataLines();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
