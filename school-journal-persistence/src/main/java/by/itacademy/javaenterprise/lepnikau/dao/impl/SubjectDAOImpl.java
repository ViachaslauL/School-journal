package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.SubjectDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
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
public class SubjectDAOImpl implements SubjectDAO {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Subject save(Subject subject) {
        if (subject == null) throw new IllegalArgumentException();

        try {
            entityManager.persist(subject);
            return subject;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public Subject get(Long id) {
        if (id == null) throw new IllegalArgumentException();

        try {
            TypedQuery<Subject> query = entityManager.createQuery(
                    "select s from Subject s left join fetch s.teachers where s.subjectId=:id",
                    Subject.class
            );

            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> getAll() {
        try {
            return entityManager
                    .createQuery("select s from Subject s left join fetch s.teachers", Subject.class)
                    .getResultList();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public boolean update(Subject subject) {
        if (subject == null) throw new IllegalArgumentException();

        try {
            entityManager.merge(subject);
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(Subject subject) {
        if (subject == null) throw new IllegalArgumentException();

        try {
            Subject foundedSubject =
                    entityManager.find(Subject.class, subject.getSubjectId());

            if (foundedSubject != null) {
                entityManager.remove(foundedSubject);
                return true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }
}
