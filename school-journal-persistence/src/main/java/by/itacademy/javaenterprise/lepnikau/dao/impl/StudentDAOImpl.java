package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.StudentDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private static final Logger LOG = LoggerFactory.getLogger(StudentDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            entityManager.getTransaction().rollback();
        }
        return student;
    }

    @Override
    public Student get(Long id) {
        if (id == null) throw new IllegalArgumentException();

        Student student = null;

        try {
            student = entityManager.find(Student.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return student;
    }

    @Override
    public boolean update(Student student) {
        if (student == null) throw new IllegalArgumentException();

        Student fondedMark = null;

        try {
            fondedMark = entityManager.find(Student.class, student.getId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (fondedMark != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(student);
                entityManager.getTransaction().commit();
                return true;
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                entityManager.getTransaction().rollback();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Student student) {
        if (student == null) throw new IllegalArgumentException();

        try {
            student = entityManager.find(Student.class, student.getId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (student != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(student);
                entityManager.getTransaction().commit();
                return true;
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                entityManager.getTransaction().rollback();
            }
        }
        return false;
    }
}
