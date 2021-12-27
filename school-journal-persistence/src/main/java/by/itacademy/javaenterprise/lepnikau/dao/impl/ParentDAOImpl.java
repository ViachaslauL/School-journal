package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.ParentDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ParentDAOImpl implements ParentDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ParentDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Parent save(Parent parent) {
        if (parent == null) throw new IllegalArgumentException();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(parent);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            entityManager.getTransaction().rollback();
        }
        return parent;
    }

    @Override
    public Parent get(Long id) {
        if (id == null) throw new IllegalArgumentException();

        Parent parent = null;

        try {
            parent = entityManager.find(Parent.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return parent;
    }

    @Override
    public boolean update(Parent parent) {
        if (parent == null) throw new IllegalArgumentException();

        Parent fondedParent = null;

        try {
            fondedParent = entityManager.find(Parent.class, parent.getId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (fondedParent != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(parent);
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
    public boolean delete(Parent parent) {
        if (parent == null) throw new IllegalArgumentException();

        try {
            parent = entityManager.find(Parent.class, parent.getId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (parent != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(parent);
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
