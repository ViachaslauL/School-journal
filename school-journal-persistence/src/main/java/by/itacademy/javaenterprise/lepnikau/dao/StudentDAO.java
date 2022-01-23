package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.Student;

import java.util.List;
import java.util.Set;

public interface StudentDAO extends DAO<Student> {

    Set<Student> getAll();

    Set<Student> getStudentsBySchoolClassId(Long id);
}
