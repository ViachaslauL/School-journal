package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.Subject;

import java.util.Set;

public interface SubjectDAO extends DAO<Subject>{

    Set<Subject> getAll(int pageNumber, int pageSize);

}
