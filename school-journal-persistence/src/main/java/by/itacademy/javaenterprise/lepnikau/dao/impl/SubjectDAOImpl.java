package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.SubjectDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        try{
            return entityManager.find(Subject.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        return entityManager
                .createQuery("select s from Subject s", Subject.class)
                .getResultList();
    }

    @Override
    public boolean update(Subject entity) {
        return false;
    }

    @Override
    public boolean delete(Subject entity) {
        return false;
    }
}
