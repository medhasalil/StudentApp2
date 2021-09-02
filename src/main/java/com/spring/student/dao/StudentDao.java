package com.spring.student.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;



import com.spring.student.model.Student;

@Component
public class StudentDao {
	
	@Autowired
	private JdbcTemplate template;
	
	public List<Student>getStudentData(){
		String sql="select * from student";
		List<Student> studentList=template.query(sql,new ResultSetExtractor<List<Student>>() {
			

			@Override
			public List<Student> extractData(ResultSet rs)throws SQLException,DataAccessException{
				
				List<Student>list=new ArrayList<>();
				
				while(rs.next()) {
					
					Student student =new Student();
					student.setId(rs.getInt("id"));
					student.setName(rs.getString("name"));
					student.setAge(Integer.parseInt(rs.getString("age")));
					list.add(student);
					
				}
				return list;
			}
			
			});
					studentList.stream().forEach(student->{
						
						System.out.println(student.getId()+"-"+student.getName());
					});
					
					return studentList;
			
		}
	
	public List<Student> getStudentDataBasedOnId(int id){
		String sql="select * from student where id= "+id;
		
		List<Student>studentList=template.query(sql, new ResultSetExtractor<List<Student>>() {
			
			@Override
			public List<Student>extractData(ResultSet rs)throws SQLException,DataAccessException{
				
				List<Student>list=new ArrayList<>();
				while(rs.next()) {
					
					Student student=new Student();
					student.setId(rs.getInt("id"));
					student.setName(rs.getString("name"));
					student.setAge(Integer.parseInt(rs.getString("age")));
						
				}
				return list;
				
			}
			
		});
		studentList.stream().forEach(student->{
			
System.out.println(student.getId()+"-"+student.getName());
			
	});
		return studentList;
	}
		
		
	//insert a data
	
	public boolean addStudentToDb(Student st) {
		boolean status=false;
		
		try {
			String sql="Insert into student (Id,name)values("+st.getId()+",'"+st.getName()+"')";	
			
			template.execute(sql);
			status=true;
			
	}catch(Exception e) {
		status=false;
		
	}
		return status;
	}

	public boolean deleteStudentFromDb(Long id) {
		boolean status=false;
		try {
			String sql="delete from student where id="+id;
			template.execute(sql);
			status=true;
		}
		catch(Exception e) {
			status=false;
		}
		return status;
	}
	
	
	
	
	
	}


