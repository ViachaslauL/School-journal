package by.itacademy.javaenterprise.lepnikau.service;

import by.itacademy.javaenterprise.lepnikau.dao.ParentDAO;
import by.itacademy.javaenterprise.lepnikau.dao.StudentDAO;
import by.itacademy.javaenterprise.lepnikau.dto.StudentDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Parent;
import by.itacademy.javaenterprise.lepnikau.entity.Student;
import by.itacademy.javaenterprise.lepnikau.modelmapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final StudentDAO studentDAO;
    private final StudentMapper studentMapper;
    private final ParentDAO parentDAO;

    @Autowired
    public StudentService(
            StudentDAO studentDAO,
            StudentMapper studentMapper,
            ParentDAO parentDAO
    ) {
        this.studentDAO = studentDAO;
        this.studentMapper = studentMapper;
        this.parentDAO = parentDAO;
    }

    public StudentDTO findStudent(Long id) {
        return studentMapper.toDto(studentDAO.get(id));
    }

    public Set<StudentDTO> findAllStudents() {
        LinkedHashSet<StudentDTO> studentsDTO = new LinkedHashSet<>();

        for (Student student : studentDAO.getAll()) {
            studentsDTO.add(studentMapper.toDto(student));
        }

        return studentsDTO;
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student savedStudent =
                studentDAO.save(studentMapper.toEntity(studentDTO));

        for (Parent parent : savedStudent.getParents()) {
            parent.setStudentId(savedStudent.getId());
            parentDAO.save(parent);
        }

        return studentMapper.toDto(savedStudent);
    }

    public boolean updateStudent(StudentDTO studentDTO) {
        return studentDAO.update(studentMapper.toEntity(studentDTO));
    }

    public boolean deleteStudent(StudentDTO studentDTO) {
        return studentDAO.delete(studentMapper.toEntity(studentDTO));
    }
}
