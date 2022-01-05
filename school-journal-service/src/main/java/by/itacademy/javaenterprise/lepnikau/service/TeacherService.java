package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.TeacherDAO;
import by.itacademy.javaenterprise.lepnikau.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherDAO teacherDAO;

    public Teacher findTeacher(Long id) {
        return teacherDAO.get(id);
    }

    public List<Teacher> findAllTeachers() {
        return teacherDAO.getAll();
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherDAO.save(teacher);
    }

    public boolean updateTeacher(Teacher teacher) {
        return teacherDAO.update(teacher);
    }

    public boolean deleteTeacher(Teacher teacher) {
        return teacherDAO.delete(teacher);
    }
}
