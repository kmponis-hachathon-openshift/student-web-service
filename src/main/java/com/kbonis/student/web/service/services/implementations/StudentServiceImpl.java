package com.kbonis.student.web.service.services.implementations;

import com.kbonis.student.web.service.models.Student;
import com.kbonis.student.web.service.repository.StudentRepository;
import com.kbonis.student.web.service.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        log.info("Creating a student");
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        log.info("Updating a student");
        return studentRepository.save(student);
    }

    @Override
    public void delete(String studentId) {
        log.info("Deleting a student");
        studentRepository.delete(studentId);
    }

    @Override
    public Student findByStudentId(String studentId) {
        log.info("Finding a student by student id");
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        log.info("Finding students by Last Name");
        return studentRepository.findByLastName(lastName);
    }

    @Override
    public List<Student> findAll() {
        log.info("Finding all students");
        return studentRepository.findAll();
    }

}
