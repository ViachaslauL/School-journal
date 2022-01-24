package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.TeacherDTO;
import by.itacademy.javaenterprise.lepnikau.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TeacherDTO> findAllTeachers(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return teacherService.findAllTeachers(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TeacherDTO findTeacher(@PathVariable Long id) {
        return teacherService.findTeacher(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TeacherDTO saveTeacher(@RequestBody TeacherDTO teacherDTO) {
       return teacherService.saveTeacher(teacherDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.updateTeacher(teacherDTO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.deleteTeacher(teacherDTO);
    }
}
