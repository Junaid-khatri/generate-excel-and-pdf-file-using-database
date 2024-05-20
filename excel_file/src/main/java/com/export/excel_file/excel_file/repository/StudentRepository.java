package com.export.excel_file.excel_file.repository;

import com.export.excel_file.excel_file.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
