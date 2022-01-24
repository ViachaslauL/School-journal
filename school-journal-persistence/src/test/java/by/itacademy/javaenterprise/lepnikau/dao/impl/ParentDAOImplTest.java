package by.itacademy.javaenterprise.lepnikau.dao.impl;

import by.itacademy.javaenterprise.lepnikau.entity.Parent;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParentDAOImplTest {

    @Mock
    private EntityManager entityManager;
    @Mock
    private TypedQuery<Parent> typedQuery;

    private ParentDAOImpl parentDAO;
    private Parent parent;

    @BeforeEach
    void beforeEach() {
        parentDAO = new ParentDAOImpl();
        parentDAO.setEntityManager(entityManager);

        parent = new Parent();
        parent.setId(1L);
        parent.setLastName("xxx");
        parent.setFirstName("xxx");
        parent.setPatronymic("xxx");
        parent.setStudentId(1L);
    }

    @Test
    void saveTest() {

        Parent actual = parentDAO.save(parent);

        verify(entityManager, times(1)).persist(parent);

        assertNotNull(actual);

        assertEquals(parent, actual);

    }

    @Test
    void saveTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> parentDAO.save(null));

    }

    @Test
    void getTest() {

        when(entityManager.find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId())))
                .thenReturn(parent);

        assertEquals(parent, parentDAO.get(parent.getId()));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId()));
    }

    @Test
    void getTestWithWrongId() {
        parent.setId(-1L);

        when(entityManager.find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId())))
                .thenReturn(null);

        assertNull(parentDAO.get(parent.getId()));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId()));
    }

    @Test
    void getTestWithEntityIdIsNull() {
        parent.setId(null);

        when(entityManager.find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId()));
    }

    /*@Test
    void getAll() {

        List<Parent> parents = new ArrayList<>();

        when(entityManager.createQuery(anyString(), Mockito.<Class<Parent>>any()))
                .thenReturn(typedQuery);

        when(typedQuery.getResultList()).thenReturn(parents);

        assertEquals(parents, parentDAO.getAll());

    }*/

    @Test
    void updateTest() {

        when(entityManager.merge(parent)).thenReturn(parent);

        assertTrue(parentDAO.update(parent));

        verify(entityManager, times(1)).merge(parent);
    }

    @Test
    void updateTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> parentDAO.update(null));

    }

    @Test
    void deleteTest() {

        when(entityManager.find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId())))
                .thenReturn(parent);

        doNothing().when(entityManager).remove(Mockito.<Class<Parent>>any());

        assertTrue(parentDAO.delete(parent));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId()));

        verify(entityManager, times(1))
                .remove(Mockito.<Class<Parent>>any());
    }

    @Test
    void deleteTestWithWrongEntityId() {
        parent.setId(-1L);

        when(entityManager.find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId())))
                .thenReturn(null);

        assertFalse(parentDAO.delete(parent));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId()));
    }

    @Test
    void deleteTestWithEntityIdIsNull() {
        parent.setId(null);

        when(entityManager.find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId())))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,
                () -> entityManager.find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId())));

        verify(entityManager, times(1))
                .find(Mockito.<Class<Parent>>any(), Mockito.eq(parent.getId()));
    }

    @Test
    void deleteTestWithArgumentIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> parentDAO.delete(null));

    }
}