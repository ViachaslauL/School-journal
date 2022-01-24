package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.ParentDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Parent;
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
            entityManager.persist(parent);
            return parent;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
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
    public List<Parent> getAll(int pNumber, int pSize) {
        try {
            TypedQuery<Parent> query = entityManager
                    .createQuery("select p from Parent p", Parent.class);

            query.setFirstResult((pNumber - 1) * pSize);
            query.setMaxResults(pSize);

            return query.getResultList();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
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
