package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
import by.itacademy.javaenterprise.lepnikau.service.SchoolClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school_class")
public class SchoolClassController {

    private static final Logger LOG = LoggerFactory.getLogger(SchoolClassController.class);

    @Autowired
    private SchoolClassesService schoolClassesService;

    @GetMapping("/find")
    public SchoolClass findSchoolClass(@RequestBody String schoolClassId) {
        return schoolClassesService.findSchoolClass(Long.parseLong(schoolClassId));
    }

    @GetMapping("/find/all")
    public List<SchoolClass> findAllSchoolClasses() {
        return schoolClassesService.findAllSchoolClasses();
    }

    @PostMapping("/save")
    public SchoolClass saveSchoolClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassesService.saveSchoolClass(schoolClass);
    }

    @PostMapping("/update")
    public boolean updateSchoolClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassesService.updateSchoolClass(schoolClass);
    }

    @PostMapping("/delete")
    public boolean deleteSchoolClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassesService.deleteSchoolClass(schoolClass);
    }
}
