package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.SchoolClassDAO;
import by.itacademy.javaenterprise.lepnikau.dao.StudentDAO;
import by.itacademy.javaenterprise.lepnikau.dto.SchoolClassDTO;
import by.itacademy.javaenterprise.lepnikau.dto.StudentDTO;
import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
import by.itacademy.javaenterprise.lepnikau.entity.Student;
import by.itacademy.javaenterprise.lepnikau.modelmapper.SchoolClassMapper;
import by.itacademy.javaenterprise.lepnikau.modelmapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolClassesService {

    private final SchoolClassDAO schoolClassDAO;
    private final StudentDAO studentDAO;
    private final SchoolClassMapper schoolClassMapper;
    private final StudentMapper studentMapper;

    @Autowired
    public SchoolClassesService(
            SchoolClassDAO schoolClassDAO,
            StudentDAO studentDAO,
            SchoolClassMapper schoolClassMapper,
            StudentMapper studentMapper
    ) {
        this.schoolClassDAO = schoolClassDAO;
        this.studentDAO = studentDAO;
        this.schoolClassMapper = schoolClassMapper;
        this.studentMapper = studentMapper;
    }

    public SchoolClassDTO findSchoolClass(Long id) {
        return schoolClassMapper.toDto(schoolClassDAO.get(id));
    }

    public SchoolClassDTO findSchoolClassWithStudents(Long id) {

        SchoolClass schoolClass = schoolClassDAO.get(id);
        List<StudentDTO> students = new ArrayList<>();

        for (Student student : studentDAO.getStudentsBySchoolClassId(schoolClass.getId())) {
            students.add(studentMapper.toDto(student));
        }

        SchoolClassDTO schoolClassDTO = schoolClassMapper.toDto(schoolClass);
        schoolClassDTO.setStudents(students);

        return schoolClassDTO;
    }

    public List<SchoolClassDTO> findAllSchoolClasses(int pNumber, int pSize) {
        List<SchoolClassDTO> classesDto = new ArrayList<>();

        for (SchoolClass schoolClass : schoolClassDAO.getAll(pNumber, pSize)) {
            classesDto.add(schoolClassMapper.toDto(schoolClass));
        }
        return classesDto;
    }

    public SchoolClassDTO saveSchoolClass(SchoolClassDTO schoolClassDTO) {
        SchoolClass schoolClass = schoolClassMapper.toEntity(schoolClassDTO);
        SchoolClass savedSchoolClass = schoolClassDAO.save(schoolClass);

        return schoolClassMapper.toDto(savedSchoolClass);
    }

    public boolean updateSchoolClass(SchoolClassDTO schoolClassDTO) {
        return schoolClassDAO.update(schoolClassMapper.toEntity(schoolClassDTO));
    }

    public boolean deleteSchoolClass(SchoolClassDTO schoolClassDTO) {
        return schoolClassDAO.delete(schoolClassMapper.toEntity(schoolClassDTO));
    }
}
