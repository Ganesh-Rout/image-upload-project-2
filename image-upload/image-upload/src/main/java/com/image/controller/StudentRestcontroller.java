package com.image.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.image.entity.Student;
import com.image.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentRestcontroller {

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/image";

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String home() {
		return "this is the home controller";
	}

	@PostMapping("/student")
	public Student createStudent(@ModelAttribute Student student, @RequestParam("image") MultipartFile mfile)
			throws IOException {
		String originalFilename = mfile.getOriginalFilename();
		Path path = Paths.get(uploadDirectory, originalFilename);
		Files.write(path, mfile.getBytes());
		student.setProfileImage(originalFilename);
		Student student2 = studentService.saveStudent(student);
		return student2;
	}

	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable("id") int id) {
		Student student = studentService.getStudentById(id);
		return student;
	}

	@GetMapping("/allStudent")
	public List<Student> allStudents() {
		List<Student> allStudent = studentService.getAllStudent();
		return allStudent;
	}

	@GetMapping("/student/profileImage/{id}")
	public ResponseEntity<Resource> getProfileImage(@PathVariable("id") int id) throws IOException {
		Student student = studentService.getStudentById(id);
		Path path = Paths.get(uploadDirectory, student.getProfileImage());
		Resource resource = new FileSystemResource(path.toFile());
		String contentType = Files.probeContentType(path);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);

	}

}
