package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
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
class SchoolClassDAOImplTest {

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<SchoolClass> typedQuery;

    private SchoolClassDAOImpl schoolClassDAO;
    private SchoolClass schoolClass;

    @BeforeEach
    void beforeEach() {
        schoolClassDAO = new SchoolClassDAOImpl();
        schoolClassDAO.setEntityManager(entityManager);

        schoolClass = new SchoolClass();
        schoolClass.setId(1L);
        schoolClass.setNumber(1);
        schoolClass.setLetter("x");
    }

    @Test
    void saveTest() {

        SchoolClass actual = schoolClassDAO.save(schoolClass);

        verify(entityManager, times(1)).persist(schoolClass);

        assertNotNull(actual);

        assertEquals(schoolClass, actual);

    }

    @Test
    void saveTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> schoolClassDAO.save(null));

    }

    @Test
    void getTest() {

        when(entityManager.find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId())))
                .thenReturn(schoolClass);

        assertEquals(schoolClass, schoolClassDAO.get(schoolClass.getId()));

        verify(entityManager, times(1))
                .find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId()));
    }

    @Test
    void getTestWithWrongId() {
        schoolClass.setId(-1L);

        when(entityManager.find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId())))
                .thenReturn(null);

        assertNull(schoolClassDAO.get(schoolClass.getId()));

        verify(entityManager, times(1))
                .find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId()));
    }

    @Test
    void getTestWithEntityIdIsNull() {
        schoolClass.setId(null);

        when(entityManager.find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId()));
    }

    @Test
    void getAll() {

        List<SchoolClass> schoolClasses = new ArrayList<>();

        when(entityManager.createQuery(anyString(), Mockito.<Class<SchoolClass>>any()))
                .thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(schoolClasses);

        assertEquals(schoolClasses, schoolClassDAO.getAll());

    }

    @Test
    void updateTest() {

        when(entityManager.merge(schoolClass)).thenReturn(schoolClass);

        assertTrue(schoolClassDAO.update(schoolClass));

        verify(entityManager, times(1)).merge(schoolClass);
    }

    @Test
    void updateTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> schoolClassDAO.update(null));

    }

    @Test
    void deleteTest() {

        when(entityManager.find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId())))
                .thenReturn(schoolClass);

        doNothing().when(entityManager).remove(Mockito.<Class<SchoolClass>>any());

        assertTrue(schoolClassDAO.delete(schoolClass));

        verify(entityManager, times(1))
                .find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId()));

        verify(entityManager, times(1))
                .remove(Mockito.<Class<SchoolClass>>any());
    }

    @Test
    void deleteTestWithWrongEntityId() {
        schoolClass.setId(-1L);

        when(entityManager.find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId())))
                .thenReturn(null);

        assertFalse(schoolClassDAO.delete(schoolClass));

        verify(entityManager, times(1))
                .find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId()));
    }

    @Test
    void deleteTestWithEntityIdIsNull() {
        schoolClass.setId(null);

        when(entityManager.find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<SchoolClass>>any(), Mockito.eq(schoolClass.getId()));
    }

    @Test
    void deleteTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> schoolClassDAO.delete(null));

    }
}