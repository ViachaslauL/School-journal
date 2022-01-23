package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Mark;
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
public class MarkDAOImpl implements MarkDAO {

    private static final Logger LOG = LoggerFactory.getLogger(MarkDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Mark get(Long receivedId) {
        if (receivedId == null) throw new IllegalArgumentException();

        try {
            TypedQuery<Mark> query = entityManager.createQuery(
                    "select m from Mark m " +
                            "left join fetch m.student st " +
                            "left join fetch st.parents " +
                            "left join fetch m.subject sb " +
                            "left join fetch sb.teachers where m.id=:receivedId",
                    Mark.class
            );
            query.setParameter("receivedId", receivedId);
            return query.getSingleResult();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public List<Mark> getAll() {
        try {
            return entityManager
                    .createQuery(
                            "select m from Mark m " +
                                    "left join fetch m.student st " +
                                    "left join fetch st.parents " +
                                    "left join fetch m.subject sb " +
                                    "left join fetch sb.teachers",
                            Mark.class
                    ).getResultList();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return new ArrayList<>();
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
