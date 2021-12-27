package by.itacademy.javaenterprise.lepnikau.dao;

public interface DAO<T> {

    T save(T t);

    T get(Long id);

    boolean update(T entity);

    boolean delete(T entity);

}
