package com.export.excel_file.excel_file.controller;

import com.export.excel_file.excel_file.entity.Student;
import com.export.excel_file.excel_file.generate_excel_pdf.ExcelGenerator;
import com.export.excel_file.excel_file.generate_excel_pdf.PdfGenerator;
import com.export.excel_file.excel_file.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/download")
public class Controller {

    @Autowired
    StudentService service;

    @GetMapping("/excel-file")
    public void generateExcelFile(HttpServletResponse response) throws IOException {

        List<Student> list = service.StudentList();
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ExcelGenerator excelGenerator = new ExcelGenerator(list);
        excelGenerator.generateExcelFile(response);
    }

    @GetMapping("/pdf-file")
    public void generatePDFFile(HttpServletResponse response) throws IOException {
        List<Student> students = service.StudentList();

        response.setContentType("application/pdf");

        DateFormat dateFormetter = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDate = dateFormetter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = student "+currentDate+".pdf";
        response.setHeader(headerKey,headerValue);

        PdfGenerator pdfGenerator = new PdfGenerator(students,response);
        pdfGenerator.generator();
    }
}
