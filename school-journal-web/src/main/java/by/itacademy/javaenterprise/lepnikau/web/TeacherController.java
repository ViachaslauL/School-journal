package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.TeacherDTO;
import by.itacademy.javaenterprise.lepnikau.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<TeacherDTO> findAllTeachers(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return teacherService.findAllTeachers(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public TeacherDTO findTeacher(@PathVariable Long id) {
        return teacherService.findTeacher(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public TeacherDTO saveTeacher(@RequestBody TeacherDTO teacherDTO) {
       return teacherService.saveTeacher(teacherDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public boolean updateTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.updateTeacher(teacherDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.deleteTeacher(teacherDTO);
    }
}
