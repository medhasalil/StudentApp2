package com.spring.student.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.student.dao.StudentDao;
import com.spring.student.model.Student;

@Service

public class StudentService {
	
	@Autowired

	private StudentDao dao;
	
	public List<Student> getStudentData() {
		
		List<Student> stList= dao.getStudentData();
		return stList;
		
	}
		
	public Student getStudentDataBasedOnId(int id) {
		
		
	List<Student> stList= dao.getStudentDataBasedOnId(id);
	return stList.get(0);
	
			
		
	}

	
	
	//insert new data
	
	
	public boolean addStudentToDb(Student st) {
		boolean status=dao.addStudentToDb(st);
		
		return status;
	}

	public boolean deleteStudentFromDb(Long id) {
		boolean status=dao.deleteStudentFromDb(id);
		
		return status;
	}

	
	

	
}
