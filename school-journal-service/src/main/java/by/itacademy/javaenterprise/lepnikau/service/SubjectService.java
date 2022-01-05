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

    public Subject findSubject(Long id) {
        return subjectDAO.get(id);
    }

    public boolean updateSubject(Subject subject) {
        return subjectDAO.update(subject);
    }

    public boolean deleteSubject(Subject subject) {
        return subjectDAO.delete(subject);
    }

    public List<Subject> findAllSubjects() {
        return subjectDAO.getAll();
    }

}
