package com.export.excel_file.excel_file;

import com.export.excel_file.excel_file.entity.Student;
import com.export.excel_file.excel_file.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ExcelFileApplication implements ApplicationRunner {

	String [] arr = {"Ram","Mohan","Sohan","Ayan","Amin","Akshay","John","Smith"};


	@Autowired
	StudentService service;

	public static void main(String[] args) {
		SpringApplication.run(ExcelFileApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Random random = new Random();

		for(int i=0;i<arr.length;i++){

			service.save(
					new Student().builder()
							.age(17)
							.name(arr[i])
							.mobileNumber(random.nextInt(900000000) + 1000000000L)
							.classTeacher("Mr. SatyaNarayna")
							.build()

			);
		}
	}

}
