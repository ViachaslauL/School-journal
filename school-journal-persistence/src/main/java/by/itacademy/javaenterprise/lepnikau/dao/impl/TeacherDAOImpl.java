package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.TeacherDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TeacherDAOImpl implements TeacherDAO {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Teacher get(Long id) {
        if (id == null) throw new IllegalArgumentException();

        try {
            return entityManager.find(Teacher.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }

    @Override
    @Transactional
    public List<Teacher> getAll() {
        return entityManager
                .createQuery("select t from Teacher t", Teacher.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Teacher save(Teacher teacher) {
        if (teacher == null) throw new IllegalArgumentException();

        try {
            entityManager.persist(teacher);
            return teacher;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }

    @Override
    @Transactional
    public boolean update(Teacher teacher) {
        if (teacher == null) throw new IllegalArgumentException();

        try {
            entityManager.merge(teacher);
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(Teacher teacher) {
        if (teacher == null) throw new IllegalArgumentException();

        try {
            Teacher foundedTeacher =
                    entityManager.find(Teacher.class, teacher.getId());

            if (foundedTeacher != null) {
                entityManager.remove(foundedTeacher);
                return true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }
}
