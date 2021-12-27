package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.SubjectDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectDAO subjectDAO;

    @Autowired
    public SubjectService(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    public Subject saveSubject(Subject subject) {
        return subjectDAO.save(subject);
    }

    public List<Subject> allSubjects() {
        return subjectDAO.findAll();
    }

}
