package com.kbonis.student.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kbonis.student.web.service.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kbonis.student.web.service.models.Student;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StudentEndpointTests {
    
    final String BASE_PATH = "http://localhost:8888/student";

	@Autowired
	private StudentRepository repository;
	
    private RestTemplate restTemplate;
    
    private ObjectMapper MAPPER = new ObjectMapper();
    
    @Before
    public void setUp() throws Exception {
        repository.deleteAll();

        repository.save(new Student("Diego", "Samuel", "0799999999", "diego.samuel@asd.asd"));
        repository.save(new Student("Eudes", "Silva", "098989898", "eudes.silva@asd.asd"));
        repository.save(new Student("Anderson", "Silva", "123124", "anderson.silve@asd.asd"));
		repository.save(new Student("Alice", "Ferreira", "435345", "alice.ferreira@asd.asd"));
		repository.save(new Student("Alan", "Franco", "123124", "alan.franco@asd.asd"));
       
        restTemplate = new RestTemplate();
    }
    
    @Test
    public void testCreateStudent() throws JsonProcessingException{
        restTemplate.delete(BASE_PATH + "/products");

        Student student = new Student("Leandro", "Costa", "12331", "leandro.costa@asd.asd");
        Student response = restTemplate.postForObject(BASE_PATH, student, Student.class);
        Assert.assertEquals("Leandro Costa", response.getFirstName() + " " + response.getLastName());
    }
    
    @Test
    public void testFindByStudentId() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Student> students = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Student.class));
        Student student = restTemplate.getForObject(BASE_PATH + "/findByStudentId/" + students.get(1).getStudentId(), Student.class);
        Assert.assertNotNull(student);
    	Assert.assertEquals("Eudes", student.getFirstName());
    	Assert.assertEquals("Silva", student.getLastName());
    }

    @Test
    public void testFindByLastName() throws IOException{
        String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Student> students = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Student.class));
        List<Student> studentsByLastName = restTemplate.getForObject(BASE_PATH + "/findByLastName/" + students.get(2).getLastName(), ArrayList.class);
        Assert.assertNotNull(studentsByLastName);
        assertThat(studentsByLastName).hasSize(2).extracting("firstName").contains("Eudes", "Anderson");
        assertThat(studentsByLastName).hasSize(2).extracting("lastName").contains("Silva", "Silva");
        assertThat(studentsByLastName).hasSize(2).extracting("phone").contains("098989898", "123124");
        assertThat(studentsByLastName).hasSize(2).extracting("email").contains("eudes.silva@asd.asd", "anderson.silve@asd.asd");
    }
    
    @Test
    public void testUpdateStudent() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Student> students = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Student.class));

        Student student = restTemplate.getForObject(BASE_PATH + "/findByStudentId/" + students.get(2).getStudentId(), Student.class);
        student.setFirstName("Tiago");
        student.setLastName("Ferreira");
        restTemplate.put(BASE_PATH, student);

        Student student2 = restTemplate.getForObject(BASE_PATH + "/findByStudentId/" + students.get(2).getStudentId(), Student.class);
        Assert.assertNotNull(student2);
    	Assert.assertEquals("Tiago", student2.getFirstName());
    	Assert.assertEquals("Ferreira", student2.getLastName());
        
    }
    
    @Test
    public void testFindAll() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/findAll", String.class);
        List<Student> students = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Student.class));
    	Assert.assertEquals("Diego", students.get(0).getFirstName());
    	Assert.assertEquals("Samuel", students.get(0).getLastName());
    }

}

//https://dzone.com/articles/unit-and-integration-tests-in-spring-boot