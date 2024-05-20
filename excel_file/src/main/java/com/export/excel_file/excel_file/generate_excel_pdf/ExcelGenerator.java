package com.export.excel_file.excel_file.generate_excel_pdf;

import com.export.excel_file.excel_file.entity.Student;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    private List<Student> studentList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;


    public ExcelGenerator(List<Student> studentList){
        this.studentList = studentList;
        this.workbook = new XSSFWorkbook();
    }

    public void writeHeader(){
        sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        createCell(row, 0,"ID",style);
        createCell(row, 1,"Name",style);
        createCell(row, 2,"MobileNumber",style);
        createCell(row, 3,"Age",style);


    }

    public  void createCell(Row row, int columCount, Object valueOfCell, CellStyle style){
        if(valueOfCell != null){
            sheet.autoSizeColumn(columCount);
            Cell cell = row.createCell(columCount);

            if (valueOfCell instanceof Integer) {
                cell.setCellValue((Integer) valueOfCell);
            }
            else if (valueOfCell instanceof Long) {
                cell.setCellValue((Long) valueOfCell);
            }
            else if (valueOfCell instanceof String) {
                cell.setCellValue((String) valueOfCell);
            }
            else {
                cell.setCellValue((Boolean) valueOfCell);
            }
        }

    }

    public void write(){
        int rowCount = 1;
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        for(Student student : studentList){
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row,columnCount++,student.getId(),style);
            createCell(row,columnCount++,student.getName(),style);
            createCell(row,columnCount++,student.getMobileNumber(),style);
            createCell(row,columnCount++,student.getAge(),style);
        }


    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
