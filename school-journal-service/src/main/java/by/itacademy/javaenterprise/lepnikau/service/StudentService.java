package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.StudentDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Student findStudent(String id) {
        Long longId = Long.valueOf(id);
        return studentDAO.get(longId);
    }

    public boolean saveStudent(Student student) {
        Student savedStudent = studentDAO.save(student);
        return savedStudent != null;
    }

}
