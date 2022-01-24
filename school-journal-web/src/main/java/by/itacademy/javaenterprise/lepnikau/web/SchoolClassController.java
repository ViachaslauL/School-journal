package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.SchoolClassDTO;
import by.itacademy.javaenterprise.lepnikau.service.SchoolClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class SchoolClassController {

    private static final Logger LOG = LoggerFactory.getLogger(SchoolClassController.class);

    private final SchoolClassesService schoolClassesService;

    @Autowired
    public SchoolClassController(SchoolClassesService schoolClassesService) {
        this.schoolClassesService = schoolClassesService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SchoolClassDTO> findAllSchoolClasses(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return schoolClassesService.findAllSchoolClasses(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SchoolClassDTO findSchoolClass(@PathVariable Long id) {
        return schoolClassesService.findSchoolClass(id);
    }

    @RequestMapping(value = "/{id}/students", method = RequestMethod.GET)
    public SchoolClassDTO findSchoolClassWithStudents(@PathVariable Long id) {
        return schoolClassesService.findSchoolClassWithStudents(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public SchoolClassDTO saveSchoolClass(@RequestBody SchoolClassDTO schoolClass) {
        return schoolClassesService.saveSchoolClass(schoolClass);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateSchoolClass(@RequestBody SchoolClassDTO schoolClassDTO) {
        return schoolClassesService.updateSchoolClass(schoolClassDTO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteSchoolClass(@RequestBody SchoolClassDTO schoolClassDTO) {
        return schoolClassesService.deleteSchoolClass(schoolClassDTO);
    }
}
