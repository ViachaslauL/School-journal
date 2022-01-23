package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.StudentDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private static final Logger LOG = LoggerFactory.getLogger(StudentDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException();

        try {
            entityManager.persist(student);
            return student;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public Student get(Long id) {
        if (id == null) throw new IllegalArgumentException();

        try {
            EntityGraph<?> entityGraph =
                    entityManager.createEntityGraph("graph.studentsParents");

            TypedQuery<Student> query = entityManager.createQuery(
                    "select s from Student s where s.id=:id",
                    Student.class
            ).setHint("javax.persistence.fetchgraph", entityGraph);

            query.setParameter("id", id);
            return query.getSingleResult();

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public Set<Student> getAll() {
        try {
            List<Student> resultList = entityManager
                    .createQuery("select s from Student s left join fetch s.parents", Student.class)
                    .getResultList();
            return new LinkedHashSet<>(resultList);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return new LinkedHashSet<>();
    }

    @Override
    @Transactional
    public boolean update(Student student) {
        if (student == null) throw new IllegalArgumentException();

        try {
            entityManager.merge(student);
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(Student student) {
        if (student == null) throw new IllegalArgumentException();

        try {
            Student foundedStudent = entityManager.find(Student.class, student.getId());
            if (foundedStudent != null) {
                entityManager.remove(foundedStudent);
                return true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    @Transactional
    public Set<Student> getStudentsBySchoolClassId(Long schoolClassId) {
        if (schoolClassId == null) throw new IllegalArgumentException();

        try {
            TypedQuery<Student> query = entityManager.createQuery(
                    "select s from Student s left join fetch s.parents where s.classId=:schoolClassId",
                    Student.class
            );
            query.setParameter("schoolClassId", schoolClassId);

            return new LinkedHashSet<>(query.getResultList());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return new LinkedHashSet<>();
    }
}
