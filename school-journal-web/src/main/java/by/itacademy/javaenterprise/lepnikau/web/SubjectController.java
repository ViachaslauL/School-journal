package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import by.itacademy.javaenterprise.lepnikau.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/find")
    public Subject findSubject(@RequestBody String id) {
        return subjectService.findSubject(Long.parseLong(id));
    }

    @GetMapping("/find/all")
    public List<Subject> findAllSubjects() {
        return subjectService.findAllSubjects();
    }

    @PostMapping("/save")
    public Subject saveSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @PostMapping("/update")
    public String updateSubject(@RequestBody Subject subject) {
        if (subjectService.updateSubject(subject)) {
            return "true";
        }
        return "false";
    }

    @PostMapping("/delete")
    public String deleteSubject(@RequestBody Subject subject) {
        if (subjectService.deleteSubject(subject)) {
            return "true";
        }
        return "false";
    }

}
