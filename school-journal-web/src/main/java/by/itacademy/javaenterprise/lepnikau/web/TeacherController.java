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

    @GetMapping
    public List<TeacherDTO> findAllTeachers() {
        return teacherService.findAllTeachers();
    }

    @GetMapping("/{id}")
    public TeacherDTO findTeacher(@PathVariable String id) {
        return teacherService.findTeacher(Long.parseLong(id));
    }

    @PostMapping("/save")
    public TeacherDTO saveTeacher(@RequestBody TeacherDTO teacherDTO) {
       return teacherService.saveTeacher(teacherDTO);
    }

    @PostMapping("/update")
    public boolean updateTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.updateTeacher(teacherDTO);
    }

    @PostMapping("/delete")
    public boolean deleteTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.deleteTeacher(teacherDTO);
    }
}
