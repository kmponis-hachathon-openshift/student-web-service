package com.kbonis.student.web.service.repository;

import com.kbonis.student.web.service.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository <Student, String> {

    Student findByStudentId(String studentId);
    List<Student> findByLastName(String lastName);
}
