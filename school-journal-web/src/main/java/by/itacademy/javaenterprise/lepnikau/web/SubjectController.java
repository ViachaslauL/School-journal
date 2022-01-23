package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.SubjectDTO;
import by.itacademy.javaenterprise.lepnikau.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectController.class);

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<SubjectDTO> findAllSubjects() {
        return subjectService.findAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectDTO findSubject(@PathVariable String id) {
        return subjectService.findSubject(Long.parseLong(id));
    }

    @PostMapping("/save")
    public SubjectDTO saveSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectService.saveSubject(subjectDTO);
    }

    @PostMapping("/update")
    public boolean updateSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectService.updateSubject(subjectDTO);
    }

    @PostMapping("/delete")
    public boolean deleteSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectService.deleteSubject(subjectDTO);
    }

}
