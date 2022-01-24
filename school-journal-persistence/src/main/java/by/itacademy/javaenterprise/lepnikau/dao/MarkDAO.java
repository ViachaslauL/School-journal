package by.itacademy.javaenterprise.lepnikau.dao;

import by.itacademy.javaenterprise.lepnikau.entity.Mark;

import java.util.List;

public interface MarkDAO extends DAO<Mark>{
    List<Mark> getAll(int pNumber, int pSize);
}
