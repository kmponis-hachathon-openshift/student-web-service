package com.kbonis.student.web.service.services;

import com.kbonis.student.web.service.models.Student;

import java.util.List;

public interface StudentService {

    /**
     * Create Student
     * @param student Student
     * @return Student
     */
    Student create(Student student);

    /**
     * Update Student
     * @param student Student
     * @return Student
     */
    Student update(Student student);

    /**
     * Delete Student
     * @param id String
     */
    void delete(String id);

    /**
     * Get Student by student id
     * @param studentId String
     * @return Student
     */
    Student findByStudentId(String studentId);

    /**
     * Get Student by lastName
     * @param lastName String
     * @return Student
     */
    List<Student> findByLastName(String lastName);

    /**
     * Get all Students
     * @return List<Student>
     */
    List<Student> findAll();

}
