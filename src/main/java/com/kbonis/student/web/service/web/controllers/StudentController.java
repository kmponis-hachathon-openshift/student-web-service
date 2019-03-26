package com.kbonis.student.web.service.web.controllers;

import com.kbonis.student.web.service.models.Student;
import com.kbonis.student.web.service.services.StudentService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "student")
@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@ApiOperation(value = "Create a new student" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Student create(@RequestBody Student student){
		return studentService.create(student);
	}

	@ApiOperation(value = "Update an existing student")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student update(@RequestBody Student student){
		return studentService.update(student);
	}

	@ApiOperation(value = "Delete student by studentId" )
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "studentId") String studentId){
        studentService.delete(studentId);
    }

	@ApiOperation(value = "Find student by studentId" )
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/findByStudentId/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findByStudentId(@PathVariable(value = "studentId") String studentId) {
		return studentService.findByStudentId(studentId);
    }

	@ApiOperation(value = "Find students by Last Name" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/findByLastName/{lastName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> findByLastName(@PathVariable(value = "lastName") String lastName){
		return studentService.findByLastName(lastName);
	}

	@ApiOperation(value = "Find all students" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> findAll(){
		return studentService.findAll();
	}

}
