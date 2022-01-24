package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;

import java.util.List;

public interface SchoolClassDAO extends DAO<SchoolClass> {
    List<SchoolClass> getAll(int pNumber, int pSize);
}
