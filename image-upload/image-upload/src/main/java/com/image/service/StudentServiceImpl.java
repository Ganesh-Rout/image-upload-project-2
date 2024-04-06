package com.image.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.image.entity.Student;
import com.image.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student saveStudent(Student student) {
		Student save = studentRepository.save(student);
		log.info("student data {}",student);
		return save;
	}

	@Override
	public Student getStudentById(int id) {
		Optional<Student> findById = studentRepository.findById(id);
		Student student = findById.get();
		return student;
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> findAll = studentRepository.findAll();
		return findAll;
	}

}
