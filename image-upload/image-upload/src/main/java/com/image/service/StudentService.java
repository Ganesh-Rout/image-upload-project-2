package com.image.service;

import java.util.List;

import com.image.entity.Student;

public interface StudentService {
	
	public Student saveStudent(Student student);

	public Student getStudentById(int id);

	public List<Student> getAllStudent();

}
