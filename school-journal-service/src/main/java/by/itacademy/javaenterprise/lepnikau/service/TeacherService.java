package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.TeacherDAO;
import by.itacademy.javaenterprise.lepnikau.dto.TeacherDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Teacher;
import by.itacademy.javaenterprise.lepnikau.modelmapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherService {

    private final TeacherDAO teacherDAO;
    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherService(
            TeacherDAO teacherDAO,
            TeacherMapper teacherMapper
    ) {
        this.teacherDAO = teacherDAO;
        this.teacherMapper = teacherMapper;
    }

    public List<TeacherDTO> findAllTeachers(int pNumber, int pSize) {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();

        for (Teacher teacher : teacherDAO.getAll(pNumber, pSize)) {
            teacherDTOList.add(teacherMapper.toDto(teacher));
        }

        return teacherDTOList;
    }

    public TeacherDTO findTeacher(Long id) {
        return teacherMapper.toDto(teacherDAO.get(id));
    }

    public TeacherDTO saveTeacher(TeacherDTO teacherDTO) {
        Teacher savedTeacher = teacherDAO.save(teacherMapper.toEntity(teacherDTO));
        return teacherMapper.toDto(savedTeacher);
    }

    public boolean updateTeacher(TeacherDTO teacherDTO) {
        return teacherDAO.update(teacherMapper.toEntity(teacherDTO));
    }

    public boolean deleteTeacher(TeacherDTO teacherDTO) {
        return teacherDAO.delete(teacherMapper.toEntity(teacherDTO));
    }
}
