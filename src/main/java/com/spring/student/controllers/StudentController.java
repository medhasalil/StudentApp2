package com.spring.student.controllers;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.student.model.Student;
import com.spring.student.service.StudentService;

@RestController

@RequestMapping("/student")
public class StudentController {

	

	@Autowired
	private StudentService service;
	
	@RequestMapping(value="/studentinfo", method=RequestMethod.GET)
	public List<Student> getStudentInfo() {
	
	List<Student>li = service.getStudentData(); 
		return li;
	}	
		
		
		
@RequestMapping(value="/postStudentData" , method= RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE ,produces= MediaType.APPLICATION_JSON_VALUE)

public Student studentPostCall(@RequestBody Student student) {

	
	if(Objects.isNull(student.getId()) ||(student.getId()==0)){
		throw new IllegalArgumentException("Student id is mandatory field or invalid argument passed");
		
	}
	
	
	//service
	
	Student studentObj=service.getStudentDataBasedOnId(student.getId());
	
	return studentObj;
}

@RequestMapping(value="/putStudentData" ,method=RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
public String putStudentData(@RequestBody Student st) {
	
	boolean status= service.addStudentToDb(st);
	
	if(status) {
		return "student added successfully";
	}
	else {
		return "student update failed";
		
	}
}

//delete a data
@RequestMapping(method=RequestMethod.DELETE,value="/deleteStudentData/{id}")
public String deleteStudent(@PathVariable("id")Long id)
{
	
		boolean status= service.deleteStudentFromDb(id);
		if(status) {
			return "Student data deletd successfully";
		}
		else {
			return  "Something went wrong";
			
		}
	}
}









		
		
		
		


