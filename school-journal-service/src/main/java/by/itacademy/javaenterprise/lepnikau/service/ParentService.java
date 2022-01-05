package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.ParentDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {

    @Autowired
    private ParentDAO parentDAO;

    public Parent find(Long id) {
        return parentDAO.get(id);
    }

    public List<Parent> findAllParents() {
        return parentDAO.getAll();
    }

    public Parent saveParent(Parent parent) {
        return parentDAO.save(parent);
    }

    public boolean updateParent(Parent parent) {
        return parentDAO.update(parent);
    }

    public boolean deleteParent(Parent parent) {
        return parentDAO.delete(parent);
    }
}
