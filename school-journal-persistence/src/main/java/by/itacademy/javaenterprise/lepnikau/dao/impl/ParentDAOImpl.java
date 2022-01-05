package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.ParentDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ParentDAOImpl implements ParentDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ParentDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Parent save(Parent parent) {
        if (parent == null) throw new IllegalArgumentException();

        try {
            entityManager.persist(parent);
            return parent;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public Parent get(Long id) {
        if (id == null) throw new IllegalArgumentException();

        try {
            return entityManager.find(Parent.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public List<Parent> getAll() {
        return entityManager
                .createQuery("select p from Parent p", Parent.class)
                .getResultList();
    }

    @Override
    @Transactional
    public boolean update(Parent parent) {
        if (parent == null) throw new IllegalArgumentException();

        try {
            entityManager.merge(parent);
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(Parent parent) {
        if (parent == null) throw new IllegalArgumentException();

        try {
            Parent foundedParent = entityManager.find(Parent.class, parent.getId());
            if (foundedParent != null) {
                entityManager.remove(foundedParent);
                return true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }
}
