package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.SchoolClassDAO;
import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolClassesService {

    private final SchoolClassDAO schoolClassDAO;

    @Autowired
    public SchoolClassesService(SchoolClassDAO schoolClassDAO) {
        this.schoolClassDAO = schoolClassDAO;
    }

    public SchoolClass findSchoolClass(Long id) {
        return schoolClassDAO.get(id);
    }

    public List<SchoolClass> findAllSchoolClasses() {
        return schoolClassDAO.getAll();
    }

    public SchoolClass saveSchoolClass(SchoolClass schoolClass) {
        return schoolClassDAO.save(schoolClass);
    }

    public boolean updateSchoolClass(SchoolClass schoolClass) {
        return schoolClassDAO.update(schoolClass);
    }

    public boolean deleteSchoolClass(SchoolClass schoolClass) {
        return schoolClassDAO.delete(schoolClass);
    }
}
