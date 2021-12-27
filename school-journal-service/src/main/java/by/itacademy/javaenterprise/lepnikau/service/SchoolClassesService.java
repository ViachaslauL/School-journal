package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.SchoolClassDAO;
import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassesService {

    private final SchoolClassDAO schoolClassDAO;

    @Autowired
    public SchoolClassesService(SchoolClassDAO schoolClassDAO) {
        this.schoolClassDAO = schoolClassDAO;
    }

    public SchoolClass findSchoolClass(String id) {
        Long longId = Long.valueOf(id);
        return schoolClassDAO.get(longId);
    }

    public boolean saveSchoolClass(SchoolClass schoolClass) {
        SchoolClass savedSchoolClass = schoolClassDAO.save(schoolClass);
        return savedSchoolClass != null;
    }
}
