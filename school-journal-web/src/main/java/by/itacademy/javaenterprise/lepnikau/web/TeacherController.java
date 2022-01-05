package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.TeacherDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import by.itacademy.javaenterprise.lepnikau.entity.Teacher;
import by.itacademy.javaenterprise.lepnikau.service.SubjectService;
import by.itacademy.javaenterprise.lepnikau.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/find")
    public TeacherDTO findTeacher(@RequestBody String id) {
        Teacher teacher = teacherService.findTeacher(Long.parseLong(id));
        Subject subject = subjectService.findSubject(teacher.getSubjectId());

        return TeacherDTO.builder()
                .id(teacher.getId())
                .lastName(teacher.getLastName())
                .firstName(teacher.getFirstName())
                .patronymic(teacher.getPatronymic())
                .subjectId(subject.getSubjectId())
                .subjectName(subject.getSubjectName())
                .build();
    }

    @GetMapping("/find/all")
    public List<TeacherDTO> findAllTeachers() {
        List<TeacherDTO> teachers = new ArrayList<>();
        List<Subject> allSubjects = subjectService.findAllSubjects();

        for (Teacher teacher : teacherService.findAllTeachers()) {
            Subject subject = new Subject();

            for (Subject s : allSubjects) {
                if (s.getSubjectId().equals(teacher.getSubjectId())) {
                    subject = s;
                }
            }

            TeacherDTO teacherDTO = TeacherDTO.builder()
                    .id(teacher.getId())
                    .lastName(teacher.getLastName())
                    .firstName(teacher.getFirstName())
                    .patronymic(teacher.getPatronymic())
                    .subjectId(subject.getSubjectId())
                    .subjectName(subject.getSubjectName())
                    .build();

            teachers.add(teacherDTO);
        }

        return teachers;
    }

    @PostMapping("/save")
    public TeacherDTO saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        Teacher teacher = Teacher.builder()
                .lastName(teacherDTO.getLastName())
                .firstName(teacherDTO.getFirstName())
                .patronymic(teacherDTO.getPatronymic())
                .subjectId(teacherDTO.getSubjectId())
                .build();

        Teacher savedTeacher = teacherService.saveTeacher(teacher);
        Subject savedTeacherSubject =
                subjectService.findSubject(savedTeacher.getSubjectId());

        return TeacherDTO.builder()
                .id(savedTeacher.getId())
                .lastName(savedTeacher.getLastName())
                .firstName(savedTeacher.getFirstName())
                .patronymic(savedTeacher.getPatronymic())
                .subjectId(savedTeacherSubject.getSubjectId())
                .subjectName(savedTeacherSubject.getSubjectName())
                .build();
    }

    @PostMapping("/update")
    public boolean updateTeacher(@RequestBody TeacherDTO teacherDTO) {
        Teacher teacher = Teacher.builder()
                .id(teacherDTO.getId())
                .lastName(teacherDTO.getLastName())
                .firstName(teacherDTO.getFirstName())
                .patronymic(teacherDTO.getPatronymic())
                .subjectId(teacherDTO.getSubjectId())
                .build();

        return teacherService.updateTeacher(teacher);
    }

    @PostMapping("/delete")
    public boolean deleteTeacher(@RequestBody TeacherDTO teacherDTO) {
        Teacher teacher = Teacher.builder()
                .id(teacherDTO.getId())
                .lastName(teacherDTO.getLastName())
                .firstName(teacherDTO.getFirstName())
                .patronymic(teacherDTO.getPatronymic())
                .subjectId(teacherDTO.getSubjectId())
                .build();

        return teacherService.deleteTeacher(teacher);
    }
}
