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

    @Autowired
    private SchoolClassesService schoolClassesService;

    @GetMapping
    public List<SchoolClassDTO> findAllSchoolClasses() {
        return schoolClassesService.findAllSchoolClasses();
    }

    @GetMapping("/{id}")
    public SchoolClassDTO findSchoolClass(@PathVariable String id) {
        return schoolClassesService.findSchoolClass(Long.parseLong(id));
    }

    @GetMapping("/{id}/students")
    public SchoolClassDTO findSchoolClassWithStudents(@PathVariable String id) {
        return schoolClassesService.findSchoolClassWithStudents(Long.parseLong(id));
    }

    @PostMapping("/save")
    public SchoolClassDTO saveSchoolClass(@RequestBody SchoolClassDTO schoolClass) {
        return schoolClassesService.saveSchoolClass(schoolClass);
    }

    @PostMapping("/update")
    public boolean updateSchoolClass(@RequestBody SchoolClassDTO schoolClassDTO) {
        return schoolClassesService.updateSchoolClass(schoolClassDTO);
    }

    @PostMapping("/delete")
    public boolean deleteSchoolClass(@RequestBody SchoolClassDTO schoolClassDTO) {
        return schoolClassesService.deleteSchoolClass(schoolClassDTO);
    }
}
