package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.StudentDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Student saveStudent(Student student) {
        return studentDAO.save(student);
    }

    public Student findStudent(String id) {
        return studentDAO.get(Long.valueOf(id));
    }

    public List<Student> findAllStudents() {
        return studentDAO.getAll();
    }

    public boolean updateStudent(Student student) {
        return studentDAO.update(student);
    }

    public boolean deleteStudent(Student student) {
        return studentDAO.delete(student);
    }
}
