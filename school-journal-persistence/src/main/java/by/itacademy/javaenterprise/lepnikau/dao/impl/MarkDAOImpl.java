package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Mark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Mark save(Mark mark) {
        if (mark == null) throw new IllegalArgumentException();

        try {
            entityManager.persist(mark);
            return mark;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
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
    @Transactional
    public boolean update(Mark mark) {
        if (mark == null) throw new IllegalArgumentException();

        Mark foundedMark = null;

        try {
            foundedMark = entityManager.find(Mark.class, mark.getId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (foundedMark != null) {
            try {
                entityManager.persist(mark);
                return true;
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return false;
    }

    @Override
    @Transactional
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
