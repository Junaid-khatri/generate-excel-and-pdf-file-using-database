package com.export.excel_file.excel_file.service;

import com.export.excel_file.excel_file.entity.Student;
import com.export.excel_file.excel_file.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public List<Student> StudentList(){
        return repository.findAll();
    }
    public void save(Student student){
        repository.save(student);
    }
}
