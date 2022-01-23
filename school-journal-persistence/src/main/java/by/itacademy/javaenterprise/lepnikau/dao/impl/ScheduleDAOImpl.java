package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.dao.ScheduleDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Schedule;
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
public class ScheduleDAOImpl implements ScheduleDAO {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduleDAOImpl.class);

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Schedule save(Schedule schedule) {
        if (schedule == null) throw new IllegalArgumentException();

        try {
            entityManager.persist(schedule);
            return schedule;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }

    @Override
    @Transactional
    public Schedule get(Long id) {
        if (id == null) throw new IllegalArgumentException();

        try {
            TypedQuery<Schedule> query = entityManager.createQuery(
                    "select s from Schedule s " +
                            "left join fetch s.subject sb " +
                            "left join fetch sb.teachers " +
                            "left join fetch s.schoolClass sc " +
                            "where s.scheduleId=:id",
                    Schedule.class
            );
            query.setParameter("id", id);

            return query.getSingleResult();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return null;
    }

    @Override
    @Transactional
    public List<Schedule> getAll() {
        try {
            return entityManager.createQuery(
                    "select s from Schedule s " +
                            "left join fetch s.subject sb " +
                            "left join fetch sb.teachers " +
                            "left join fetch s.schoolClass sc",
                    Schedule.class
            ).getResultList();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public boolean update(Schedule schedule) {
        if (schedule == null) throw new IllegalArgumentException();

        try {
            entityManager.merge(schedule);
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(Schedule schedule) {
        if (schedule == null) throw new IllegalArgumentException();

        try {
            Schedule foundedSchedule =
                    entityManager.find(Schedule.class, schedule.getScheduleId());
            if (foundedSchedule != null) {
                entityManager.remove(foundedSchedule);
                return true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return false;
    }
}
