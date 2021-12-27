package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.SchoolClassDAO;
import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class SchoolClassDAOImpl implements SchoolClassDAO {

    private static final Logger LOG = LoggerFactory.getLogger(SchoolClassDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public SchoolClass save(SchoolClass schoolClass) {
        if (schoolClass == null) throw new IllegalArgumentException();

        try {
            entityManager.persist(schoolClass);
            return schoolClass;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public SchoolClass get(Long id) {
        if (id == null) throw new IllegalArgumentException();

        SchoolClass schoolClass = null;

        try {
            schoolClass = entityManager.find(SchoolClass.class, id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return schoolClass;
    }

    @Override
    public boolean update(SchoolClass entity) {
        return false;
    }

    @Override
    public boolean delete(SchoolClass entity) {
        return false;
    }
}
