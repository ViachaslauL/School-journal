package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class StudentDAOImplTest {

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<Student> typedQuery;

    private StudentDAOImpl studentDAO;
    private Student student;

    @BeforeEach
    void beforeEach() {
        studentDAO = new StudentDAOImpl();
        studentDAO.setEntityManager(entityManager);

        student = new Student();
        student.setId(1L);
        student.setLastName("xxx");
        student.setFirstName("xxx");
        student.setPatronymic("xxx");
        student.setClassId(1L);
    }

    @Test
    void saveTest() {

        Student actual = studentDAO.save(student);

        verify(entityManager, times(1)).persist(student);

        assertNotNull(actual);

        assertEquals(student, actual);

    }

    @Test
    void saveTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> studentDAO.save(null));

    }

    @Test
    void getAll() {

        List<Student> students = new ArrayList<>();

        when(entityManager.createQuery(anyString(), Mockito.<Class<Student>>any()))
                .thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(students);

        assertEquals(new LinkedHashSet<>(students), studentDAO.getAll(1, 1));

    }

    @Test
    void updateTest() {

        when(entityManager.merge(student)).thenReturn(student);

        assertTrue(studentDAO.update(student));

        verify(entityManager, times(1)).merge(student);
    }

    @Test
    void updateTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> studentDAO.update(null));

    }

    @Test
    void deleteTest() {

        when(entityManager.find(Mockito.<Class<Student>>any(), Mockito.eq(student.getId())))
                .thenReturn(student);

        doNothing().when(entityManager).remove(Mockito.<Class<Student>>any());

        assertTrue(studentDAO.delete(student));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Student>>any(), Mockito.eq(student.getId()));

        verify(entityManager, times(1))
                .remove(Mockito.<Class<Student>>any());
    }

    @Test
    void deleteTestWithWrongEntityId() {
        student.setId(-1L);

        when(entityManager.find(Mockito.<Class<Student>>any(), Mockito.eq(student.getId())))
                .thenReturn(null);

        assertFalse(studentDAO.delete(student));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Student>>any(), Mockito.eq(student.getId()));
    }

    @Test
    void deleteTestWithEntityIdIsNull() {
        student.setId(null);

        when(entityManager.find(Mockito.<Class<Student>>any(), Mockito.eq(student.getId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<Student>>any(), Mockito.eq(student.getId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Student>>any(), Mockito.eq(student.getId()));
    }

    @Test
    void deleteTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> studentDAO.delete(null));

    }
}