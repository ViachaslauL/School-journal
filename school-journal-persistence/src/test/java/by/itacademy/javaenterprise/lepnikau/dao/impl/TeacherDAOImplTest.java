package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.entity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TeacherDAOImplTest {


    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<Teacher> typedQuery;

    private TeacherDAOImpl teacherDAO;
    private Teacher teacher;

    @BeforeEach
    void beforeEach() {
        teacherDAO = new TeacherDAOImpl();
        teacherDAO.setEntityManager(entityManager);

        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setLastName("xxx");
        teacher.setFirstName("xxx");
        teacher.setPatronymic("xxx");
        teacher.setSubjectId(1L);
    }

    @Test
    void saveTest() {

        Teacher actual = teacherDAO.save(teacher);

        verify(entityManager, times(1)).persist(teacher);

        assertNotNull(actual);

        assertEquals(teacher, actual);

    }

    @Test
    void saveTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> teacherDAO.save(null));

    }

    @Test
    void getTest() {

        when(entityManager.find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId())))
                .thenReturn(teacher);

        assertEquals(teacher, teacherDAO.get(teacher.getId()));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId()));
    }

    @Test
    void getTestWithWrongId() {
        teacher.setId(-1L);

        when(entityManager.find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId())))
                .thenReturn(null);

        assertNull(teacherDAO.get(teacher.getId()));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId()));
    }

    @Test
    void getTestWithEntityIdIsNull() {
        teacher.setId(null);

        when(entityManager.find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId()));
    }

    /*@Test
    void getAll() {

        List<Teacher> teachers = new ArrayList<>();

        when(entityManager.createQuery(anyString(), Mockito.<Class<Teacher>>any()))
                .thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(teachers);

        assertEquals(teachers, teacherDAO.getAll());

    }*/

    @Test
    void updateTest() {

        when(entityManager.merge(teacher)).thenReturn(teacher);

        assertTrue(teacherDAO.update(teacher));

        verify(entityManager, times(1)).merge(teacher);
    }

    @Test
    void updateTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> teacherDAO.update(null));

    }

    @Test
    void deleteTest() {

        when(entityManager.find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId())))
                .thenReturn(teacher);

        doNothing().when(entityManager).remove(Mockito.<Class<Teacher>>any());

        assertTrue(teacherDAO.delete(teacher));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId()));

        verify(entityManager, times(1))
                .remove(Mockito.<Class<Teacher>>any());
    }

    @Test
    void deleteTestWithWrongEntityId() {
        teacher.setId(-1L);

        when(entityManager.find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId())))
                .thenReturn(null);

        assertFalse(teacherDAO.delete(teacher));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId()));
    }

    @Test
    void deleteTestWithEntityIdIsNull() {
        teacher.setId(null);

        when(entityManager.find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Teacher>>any(), Mockito.eq(teacher.getId()));
    }

    @Test
    void deleteTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> teacherDAO.delete(null));

    }

}