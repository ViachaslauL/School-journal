package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Mark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class MarkDAOImpl implements MarkDAO {

    private static final Logger LOG = LoggerFactory.getLogger(MarkDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Mark save(Mark mark) {
        if (mark == null) throw new IllegalArgumentException();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(mark);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            entityManager.getTransaction().rollback();
        }
        return mark;
    }

    @Override
    public Mark get(Long id) {
        if (id == null) throw new IllegalArgumentException();

        Mark mark = null;

        try {
            mark = entityManager.find(Mark.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return mark;
    }

    @Override
    public boolean update(Mark mark) {
        if (mark == null) throw new IllegalArgumentException();

        Mark fondedMark = null;

        try {
            fondedMark = entityManager.find(Mark.class, mark.getId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (fondedMark != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(mark);
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
    public boolean delete(Mark mark) {
        if (mark == null) throw new IllegalArgumentException();

        try {
            mark = entityManager.find(Mark.class, mark.getId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (mark != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(mark);
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
