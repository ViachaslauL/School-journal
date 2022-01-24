package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.Parent;

import java.util.List;

public interface ParentDAO extends DAO<Parent>{
    List<Parent> getAll(int pNumber, int pSize);
}
