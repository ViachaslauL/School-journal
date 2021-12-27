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

    @GetMapping("/subjects")
    public String subjects() {
        StringBuilder responseBody = new StringBuilder();

        for (Subject subject : subjectService.allSubjects()) {
            responseBody
                    .append("[")
                    .append("subject id: '").append(subject.getId()).append("'")
                    .append(", ")
                    .append("subject name: '").append(subject.getName()).append("'")
                    .append("]");
        }

        return responseBody.toString();
    }

    @PostMapping("/save_subject")
    public void saveSubject(@RequestBody Subject subject) {

        LOG.info("Was an object inserted: {}",
                subjectService.saveSubject(subject).toString());

    }

}
