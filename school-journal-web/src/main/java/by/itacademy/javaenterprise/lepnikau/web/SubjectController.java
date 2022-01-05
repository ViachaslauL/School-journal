package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.SubjectDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import by.itacademy.javaenterprise.lepnikau.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/find")
    public SubjectDTO findSubject(@RequestBody String id) {
        Subject subject = subjectService.findSubject(Long.parseLong(id));

        return SubjectDTO.builder()
                .subjectId(subject.getSubjectId())
                .subjectName(subject.getSubjectName())
                .teachers(subject.getTeachers())
                .build();
    }

    @GetMapping("/find/all")
    public List<SubjectDTO> findAllSubjects() {
        List<SubjectDTO> subjectDTOs = new ArrayList<>();
        for (Subject subject : subjectService.findAllSubjects()) {
            subjectDTOs.add(
                    SubjectDTO.builder()
                            .subjectId(subject.getSubjectId())
                            .subjectName(subject.getSubjectName())
                            .teachers(subject.getTeachers())
                            .build()
            );
        }

        return subjectDTOs;
    }

    @PostMapping("/save")
    public SubjectDTO saveSubject(@RequestBody SubjectDTO subject) {

        Subject savedSubject = subjectService.saveSubject(
                Subject.builder()
                        .subjectName(subject.getSubjectName())
                        .build()
        );

        return SubjectDTO.builder()
                .subjectId(savedSubject.getSubjectId())
                .subjectName(savedSubject.getSubjectName())
                .build();
    }

    @PostMapping("/update")
    public String updateSubject(@RequestBody SubjectDTO subjectDTO) {
        Subject subject = Subject.builder()
                .subjectId(subjectDTO.getSubjectId())
                .subjectName(subjectDTO.getSubjectName())
                .build();

        boolean isUpdate = subjectService.updateSubject(subject);
        return isUpdate ? "true" : "false";
    }

    @PostMapping("/delete")
    public String deleteSubject(@RequestBody SubjectDTO subjectDTO) {
        Subject subject = Subject.builder()
                .subjectId(subjectDTO.getSubjectId())
                .subjectName(subjectDTO.getSubjectName())
                .build();

        boolean isDeleted = subjectService.deleteSubject(subject);
        return isDeleted ? "true" : "false";
    }

}
