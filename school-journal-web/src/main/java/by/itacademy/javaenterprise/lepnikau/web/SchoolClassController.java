package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
import by.itacademy.javaenterprise.lepnikau.service.SchoolClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchoolClassController {

    private static final Logger LOG = LoggerFactory.getLogger(SchoolClassController.class);

    @Autowired
    private SchoolClassesService schoolClassesService;

    @GetMapping("/school_class")
    public String getSchoolClass(@RequestParam(name = "school_class_id") String id) {
        return schoolClassesService.findSchoolClass(id).toString();
    }

    @PostMapping("/save_school_class")
    public void saveSchoolClass(@RequestBody SchoolClass schoolClass) {
        LOG.info("SchoolClassDAO return: {}",
                schoolClassesService.saveSchoolClass(schoolClass));
    }
}
