package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Mark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
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

        try {
            return entityManager.find(Mark.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public List<Mark> getAll() {
        return entityManager
                .createQuery("select m from Mark m", Mark.class)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean update(Mark mark) {
        if (mark == null) throw new IllegalArgumentException();

        try {
            entityManager.merge(mark);
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(Mark mark) {
        if (mark == null) throw new IllegalArgumentException();

        try {
            Mark foundedMark = entityManager.find(Mark.class, mark.getId());
            if (foundedMark != null) {
                entityManager.remove(foundedMark);
                return true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }
}
