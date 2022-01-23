package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.entity.Mark;
import by.itacademy.javaenterprise.lepnikau.entity.Parent;
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
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MarkDAOImplTest {

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<Mark> typedQuery;

    private MarkDAOImpl markDAO;
    private Mark mark;

    @BeforeEach
    void beforeEach() {
        markDAO = new MarkDAOImpl();
        markDAO.setEntityManager(entityManager);

        mark = Mark.builder()
                .id(1L)
                .studentId(1L)
                .mark(7)
                .subjectId(1L)
                .date(Date.valueOf(LocalDate.now()))
                .build();
    }

    @Test
    void saveTest() {

        Mark actual = markDAO.save(mark);

        verify(entityManager, times(1)).persist(mark);

        assertNotNull(actual);

        assertEquals(mark, actual);

    }

    @Test
    void saveTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> markDAO.save(null));

    }

    /*@Test
    void getTest() {

        when(entityManager.find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId())))
                .thenReturn(mark);

        assertEquals(mark, markDAO.get(mark.getId()));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId()));

    }

    @Test
    void getTestWithWrongId() {
        mark.setId(-1L);

        when(entityManager.find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId())))
                .thenReturn(null);

        assertNull(markDAO.get(mark.getId()));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId()));

    }

    @Test
    void getTestWithEntityIdIsNull() {
        mark.setId(null);

        when(entityManager.find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId()));
    }*/

    @Test
    void getAll() {

        List<Mark> marks = new ArrayList<>();

        when(entityManager.createQuery(anyString(), Mockito.<Class<Mark>>any()))
                .thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(marks);

        assertEquals(marks, markDAO.getAll());

    }

    @Test
    void updateTest() {

        when(entityManager.merge(mark)).thenReturn(mark);

        assertTrue(markDAO.update(mark));

        verify(entityManager, times(1)).merge(mark);

    }

    @Test
    void updateTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> markDAO.update(null));

    }

    @Test
    void deleteTest() {

        when(entityManager.find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId())))
                .thenReturn(mark);

        doNothing().when(entityManager).remove(Mockito.<Class<Parent>>any());

        assertTrue(markDAO.delete(mark));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId()));

        verify(entityManager, times(1))
                .remove(Mockito.<Class<Mark>>any());
    }

    @Test
    void deleteTestWithWrongEntityId() {
        mark.setId(-1L);

        when(entityManager.find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId())))
                .thenReturn(null);

        assertFalse(markDAO.delete(mark));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId()));
    }

    @Test
    void deleteTestWithEntityIdIsNull() {
        mark.setId(null);

        when(entityManager.find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Mark>>any(), Mockito.eq(mark.getId()));
    }

    @Test
    void deleteTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> markDAO.delete(null));

    }
}