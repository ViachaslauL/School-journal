package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.Teacher;

import java.util.List;

public interface TeacherDAO extends DAO<Teacher> {
    List<Teacher> getAll();
}
