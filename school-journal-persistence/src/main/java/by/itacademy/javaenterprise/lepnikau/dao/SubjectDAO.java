package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.Subject;

import java.util.List;

public interface SubjectDAO extends DAO<Subject>{

    List<Subject> findAll();

}
