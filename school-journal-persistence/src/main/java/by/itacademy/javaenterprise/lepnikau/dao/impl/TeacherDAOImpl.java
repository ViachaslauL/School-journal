package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.TeacherDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class TeacherDAOImpl implements TeacherDAO {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
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
    public List<Teacher> getAll(int pNumber, int pSize) {
        try {
            TypedQuery<Teacher> query =
                    entityManager.createQuery("select t from Teacher t", Teacher.class);

            query.setFirstResult((pNumber - 1) * pSize);
            query.setMaxResults(pSize);

            return query.getResultList();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
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
