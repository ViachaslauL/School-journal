package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import by.itacademy.javaenterprise.lepnikau.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/find_subject")
    public String findSubject(@RequestBody String id) {
        Subject subject = subjectService.findSubject(Long.parseLong(id));

        StringBuilder responseBody = new StringBuilder();
        responseBody
                .append("[")
                .append("subject id: '").append(subject.getId()).append("', ")
                .append("subject name: '").append(subject.getName()).append("'")
                .append("]");

        return responseBody.toString();
    }

    @GetMapping("/subjects")
    public String findAllSubjects() {
        StringBuilder responseBody = new StringBuilder();

        for (Subject subject : subjectService.findAllSubjects()) {
            responseBody
                    .append("[")
                    .append("subject id: '").append(subject.getId()).append("'")
                    .append(", ")
                    .append("subject name: '").append(subject.getName()).append("'")
                    .append("]\n");
        }

        return responseBody.toString();
    }

    @PostMapping("/save_subject")
    public String saveSubject(@RequestBody Subject subject) {
        Subject savedSubject = subjectService.saveSubject(subject);

        StringBuilder responseBody = new StringBuilder();

        if (savedSubject != null) {
            responseBody
                    .append("Subject: [").append(subject.toString()).append("]")
                    .append(" saved to database.");
        } else {
            responseBody
                    .append("Something gone wrong.");
        }

        return responseBody.toString();
     }

    @PostMapping("/update_subject")
    public String updateSubject(@RequestBody Subject subject) {
        boolean isUpdate = subjectService.updateSubject(subject);
        return isUpdate ? "true" : "false";
    }

    @PostMapping("/delete_subject")
    public String deleteSubject(@RequestBody Subject subject) {
        boolean isDeleted = subjectService.deleteSubject(subject);
        return isDeleted ? "true" : "false";
    }

}
