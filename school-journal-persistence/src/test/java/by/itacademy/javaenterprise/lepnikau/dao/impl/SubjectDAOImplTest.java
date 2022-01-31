package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SubjectDAOImplTest {

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<Subject> typedQuery;

    private SubjectDAOImpl subjectDAO;
    private Subject subject;

    @BeforeEach
    void beforeEach() {
        subjectDAO = new SubjectDAOImpl();
        subjectDAO.setEntityManager(entityManager);

        subject = new Subject();
        subject.setSubjectId(1L);
        subject.setSubjectName("x");
    }

    @Test
    void saveTest() {

        Subject actual = subjectDAO.save(subject);

        verify(entityManager, times(1)).persist(subject);

        assertNotNull(actual);

        assertEquals(subject, actual);

    }

    @Test
    void saveTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> subjectDAO.save(null));

    }

    @Test
    void getTest() {

        when(entityManager.createQuery(anyString(), Mockito.<Class<Subject>>any()))
                .thenReturn(typedQuery);

        when((typedQuery.getSingleResult())).thenReturn(subject);

        assertEquals(subject, subjectDAO.get(subject.getSubjectId()));

        verify(entityManager, times(1))
                .createQuery(anyString(), Mockito.<Class<Subject>>any());
    }

    @Test
    void getAll() {

        Set<Subject> Subjects = new LinkedHashSet<>();

        when(entityManager.createQuery(anyString(), Mockito.<Class<Subject>>any()))
                .thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(new ArrayList<>(Subjects));

        assertEquals(Subjects, subjectDAO.getAll(1, 1));

    }

    @Test
    void updateTest() {

        when(entityManager.merge(subject)).thenReturn(subject);

        assertTrue(subjectDAO.update(subject));

        verify(entityManager, times(1)).merge(subject);
    }

    @Test
    void updateTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> subjectDAO.update(null));

    }

    @Test
    void deleteTest() {

        when(entityManager.find(Mockito.<Class<Subject>>any(), Mockito.eq(subject.getSubjectId())))
                .thenReturn(subject);

        doNothing().when(entityManager).remove(Mockito.<Class<Subject>>any());

        assertTrue(subjectDAO.delete(subject));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Subject>>any(), Mockito.eq(subject.getSubjectId()));

        verify(entityManager, times(1))
                .remove(Mockito.<Class<Subject>>any());
    }

    @Test
    void deleteTestWithWrongEntityId() {
        subject.setSubjectId(-1L);

        when(entityManager.find(Mockito.<Class<Subject>>any(), Mockito.eq(subject.getSubjectId())))
                .thenReturn(null);

        assertFalse(subjectDAO.delete(subject));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Subject>>any(), Mockito.eq(subject.getSubjectId()));
    }

    @Test
    void deleteTestWithEntityIdIsNull() {
        subject.setSubjectId(null);

        when(entityManager.find(Mockito.<Class<Subject>>any(), Mockito.eq(subject.getSubjectId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<Subject>>any(), Mockito.eq(subject.getSubjectId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Subject>>any(), Mockito.eq(subject.getSubjectId()));
    }

    @Test
    void deleteTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> subjectDAO.delete(null));

    }

}