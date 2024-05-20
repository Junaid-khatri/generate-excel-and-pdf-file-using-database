package com.export.excel_file.excel_file.generate_excel_pdf;

import com.export.excel_file.excel_file.entity.Student;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    private List<Student> studentList;
    private HttpServletResponse response;

    public PdfGenerator(List<Student> studentList, HttpServletResponse response) {
        this.studentList = studentList;
        this.response = response;
    }

    public void generator() throws IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC);
        font.setSize(20);

        Paragraph paragraph1 = new Paragraph("List Of Students",font);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph1);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new int[] {3,3,3,3,3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.white);
        cell.setPadding(5);

        font = FontFactory.getFont(FontFactory.TIMES_BOLD);
        font.setSize(16);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name",font));
        table.addCell(cell);
        cell.setPhrase((new Phrase("Age",font)));
        table.addCell(cell);
        cell.setPhrase((new Phrase("Mobile nu.",font)));
        table.addCell(cell);
        cell.setPhrase((new Phrase("Class Teacher",font)));
        table.addCell(cell);

        for(Student student: studentList){
            table.addCell(String.valueOf(student.getId()));
            table.addCell(student.getName());
            table.addCell(String.valueOf(student.getAge()));
            table.addCell(String.valueOf(student.getMobileNumber()));
            table.addCell(student.getClassTeacher());
        }
        document.add(table);
        document.close();
    }


}
