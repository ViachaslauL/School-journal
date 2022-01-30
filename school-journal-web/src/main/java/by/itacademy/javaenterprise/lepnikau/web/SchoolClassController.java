package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.SchoolClassDTO;
import by.itacademy.javaenterprise.lepnikau.service.SchoolClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class SchoolClassController {

    private final SchoolClassesService schoolClassesService;

    @Autowired
    public SchoolClassController(SchoolClassesService schoolClassesService) {
        this.schoolClassesService = schoolClassesService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<SchoolClassDTO> findAllSchoolClasses(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return schoolClassesService.findAllSchoolClasses(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public SchoolClassDTO findSchoolClass(@PathVariable Long id) {
        return schoolClassesService.findSchoolClass(id);
    }

    @RequestMapping(value = "/{id}/students", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public SchoolClassDTO findSchoolClassWithStudents(@PathVariable Long id) {
        return schoolClassesService.findSchoolClassWithStudents(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public SchoolClassDTO saveSchoolClass(@RequestBody SchoolClassDTO schoolClass) {
        return schoolClassesService.saveSchoolClass(schoolClass);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public boolean updateSchoolClass(@RequestBody SchoolClassDTO schoolClassDTO) {
        return schoolClassesService.updateSchoolClass(schoolClassDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteSchoolClass(@RequestBody SchoolClassDTO schoolClassDTO) {
        return schoolClassesService.deleteSchoolClass(schoolClassDTO);
    }
}
