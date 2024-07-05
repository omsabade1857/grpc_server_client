package com.client.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.client.dtos.StudentDto;
import com.client.service.Grpc_Client;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private Grpc_Client grpc_Client;

	@PostMapping("/")
	public ResponseEntity<StudentDto> addNewStudent( @RequestBody StudentDto studentDto) {
		logger.debug("Received request to create address: {}", studentDto);
		System.out.println("Received request to create address: "+ studentDto);
//		StudentDto studentDto2 = studentService.createStudent(studentDto);
		StudentDto studentDto2 = grpc_Client.addStudent(studentDto);
		logger.debug("Created Student: {}", studentDto2);
		return new ResponseEntity<>(studentDto2, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
    public ResponseEntity<?> getStudent(@RequestParam(value = "id", required = false) Integer id,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "designation", required = false) String designation) {

        if (id != null) {
            StudentDto studentDto = grpc_Client.getStudentById(id);
            return new ResponseEntity<>(studentDto, HttpStatus.OK);
        } // else if (name != null) {
        //     return handleStudent.handleGetStudentByName(name);
        // } else if (designation != null) {
        //     return handleStudent.handleGetStudentByDesignation(designation);
        // }
        List<StudentDto> allStudents = grpc_Client.getAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }
	
//
//	@PutMapping("/{id}")
//	public ResponseEntity<StudentDto> updateStudentById(@PathVariable("id") int id,
//			@Valid @RequestBody StudentDto studentDto) {
//		logger.debug("Received request to update student with id: {}", id);
//		StudentDto studentDto2 = studentService.updateStudentById(id, studentDto);
//		if (studentDto2 != null) {
//			logger.debug("Updated student: {}", studentDto2);
//			return new ResponseEntity<>(studentDto2, HttpStatus.ACCEPTED);
//		} else {
//			logger.warn("student with id {} not found for update", id);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
//		logger.debug("Delete Request: ", id);
//		if (studentService.getStudentById(id) != null) {
//			studentService.deleteStudentById(id);
//			logger.warn("Your data deleted by id {}", id);
//			return ResponseEntity.status(HttpStatus.OK).body("Data Deleted !!");
//		}
//		logger.warn("Student not found by id {}", id);
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address Id Required");
//	}

}
