package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.StudentDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private static final Logger LOG = LoggerFactory.getLogger(StudentDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException();

        try {
            entityManager.persist(student);
            return student;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public Student get(Long id) {
        if (id == null) throw new IllegalArgumentException();

        try {
            return entityManager.find(Student.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public List<Student> getAll() {
        return entityManager
                .createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean update(Student student) {
        if (student == null) throw new IllegalArgumentException();

        try {
            entityManager.merge(student);
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(Student student) {
        if (student == null) throw new IllegalArgumentException();

        try {
            Student foundedStudent = entityManager.find(Student.class, student.getId());
            if (foundedStudent != null) {
                entityManager.remove(foundedStudent);
                return true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }
}
