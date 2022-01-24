package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.SchoolClassDAO;
import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
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
public class SchoolClassDAOImpl implements SchoolClassDAO {

    private static final Logger LOG = LoggerFactory.getLogger(SchoolClassDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
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

        try {
            TypedQuery<SchoolClass> query = entityManager.createQuery(
                    "select sc from SchoolClass sc where sc.id=:id",
                    SchoolClass.class
            );

            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<SchoolClass> getAll(int pNumber, int pSize) {
        try {
            TypedQuery<SchoolClass> query =
                    entityManager.createQuery("select s from SchoolClass s", SchoolClass.class);
            query.setFirstResult((pNumber - 1) * pSize);
            query.setMaxResults(pSize);

            return query.getResultList();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean update(SchoolClass schoolClass) {
        if (schoolClass == null) throw new IllegalArgumentException();

        try {
            entityManager.merge(schoolClass);
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    public boolean delete(SchoolClass schoolClass) {
        if (schoolClass == null) throw new IllegalArgumentException();

        try {
            SchoolClass foundedSchoolClass =
                    entityManager.find(SchoolClass.class, schoolClass.getId());

            if (foundedSchoolClass != null) {
                entityManager.remove(foundedSchoolClass);
                return true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }
}
