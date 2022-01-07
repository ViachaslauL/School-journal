package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.entity.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ScheduleDAOImplTest {

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<Schedule> typedQuery;

    private ScheduleDAOImpl scheduleDAO;
    private Schedule schedule;

    @BeforeEach
    void beforeEach() {
        scheduleDAO = new ScheduleDAOImpl();
        scheduleDAO.setEntityManager(entityManager);

        schedule = new Schedule();
        schedule.setScheduleId(1L);
        schedule.setClassId(1L);
        schedule.setSubjectId(1L);
        schedule.setTask("xxx");
        schedule.setDate(Date.valueOf(LocalDate.now()));
    }

    @Test
    void saveTest() {

        Schedule actual = scheduleDAO.save(schedule);

        verify(entityManager, times(1)).persist(schedule);

        assertNotNull(actual);

        assertEquals(schedule, actual);

    }

    @Test
    void saveTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> scheduleDAO.save(null));

    }

    @Test
    void getTest() {

        when(entityManager.find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId())))
                .thenReturn(schedule);

        assertEquals(schedule, scheduleDAO.get(schedule.getScheduleId()));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId()));
    }

    @Test
    void getTestWithWrongId() {
        schedule.setScheduleId(-1L);

        when(entityManager.find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId())))
                .thenReturn(null);

        assertNull(scheduleDAO.get(schedule.getScheduleId()));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId()));
    }

    @Test
    void getTestWithEntityIdIsNull() {
        schedule.setScheduleId(null);

        when(entityManager.find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId()));
    }

    @Test
    void getAll() {

        List<Schedule> schedules = new ArrayList<>();

        when(entityManager.createQuery(anyString(), Mockito.<Class<Schedule>>any()))
                .thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(schedules);

        assertEquals(schedules, scheduleDAO.getAll());

    }

    @Test
    void updateTest() {

        when(entityManager.merge(schedule)).thenReturn(schedule);

        assertTrue(scheduleDAO.update(schedule));

        verify(entityManager, times(1)).merge(schedule);
    }

    @Test
    void updateTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> scheduleDAO.update(null));

    }

    @Test
    void deleteTest() {

        when(entityManager.find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId())))
                .thenReturn(schedule);

        doNothing().when(entityManager).remove(Mockito.<Class<Schedule>>any());

        assertTrue(scheduleDAO.delete(schedule));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId()));

        verify(entityManager, times(1))
                .remove(Mockito.<Class<Schedule>>any());
    }

    @Test
    void deleteTestWithWrongEntityId() {
        schedule.setScheduleId(-1L);

        when(entityManager.find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId())))
                .thenReturn(null);

        assertFalse(scheduleDAO.delete(schedule));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId()));
    }

    @Test
    void deleteTestWithEntityIdIsNull() {
        schedule.setScheduleId(null);

        when(entityManager.find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Schedule>>any(), Mockito.eq(schedule.getScheduleId()));
    }

    @Test
    void deleteTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> scheduleDAO.delete(null));

    }
}