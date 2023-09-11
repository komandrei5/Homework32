package com.home.student;

import com.home.student.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student get(Long id);
    Student update (Student student);
    Long create(Student student);
    void delete (Student student);
}
