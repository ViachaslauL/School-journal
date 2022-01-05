package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.Student;

import java.util.List;

public interface StudentDAO extends DAO<Student> {
    List<Student> getAll();
}
