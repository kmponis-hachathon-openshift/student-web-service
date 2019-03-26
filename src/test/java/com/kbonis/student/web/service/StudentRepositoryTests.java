package com.kbonis.student.web.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.kbonis.student.web.service.models.Student;
import com.kbonis.student.web.service.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StudentRepositoryTests {

    @Autowired
    StudentRepository repository;

    Student diego, tiago, leandro;

    @Before
    public void setUp() {
        repository.deleteAll();

        diego = repository.save(new Student("Diego", "Samuel", "234234", "email@email.com"));
        tiago = repository.save(new Student("Tiago Daniel", "Samuel", "234234", "email@email.com"));
        leandro = repository.save(new Student("Leandro", "Costa", "243", "leandro.costa@asd.asd"));
    }

    @Test
    public void setsIdOnSave() {
        Student diego = repository.save(new Student("Diego", "Samuel", "234", "diego.samuel@asd.asd"));
        assertThat(diego.getStudentId()).isNotNull();
    }

    @Test
    public void findsByLastName() {
        List<Student> result = repository.findByLastName("Costa");
        assertThat(result).hasSize(1).extracting("firstName").contains("Leandro");
    }

    @Ignore
    @Test
    public void findsByExample() {
        Student probe = new Student(null, "Samuel", "123", "null.samuel@asd.null");
        List<Student> result = repository.findAll(Example.of(probe));
        assertThat(result).hasSize(2).extracting("firstName").contains("Diego", "Tiago Daniel");
    }
}
