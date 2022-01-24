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

    @RequestMapping(method = RequestMethod.GET)
    public List<SubjectDTO> findAllSubjects(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return subjectService.findAllSubjects(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SubjectDTO findSubject(@PathVariable Long id) {
        return subjectService.findSubject(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public SubjectDTO saveSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectService.saveSubject(subjectDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectService.updateSubject(subjectDTO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteSubject(@RequestBody SubjectDTO subjectDTO) {
        return subjectService.deleteSubject(subjectDTO);
    }
}
