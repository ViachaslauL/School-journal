package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.entity.Teacher;
import by.itacademy.javaenterprise.lepnikau.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/find")
    public Teacher findTeacher(@RequestBody String id) {
        return teacherService.findTeacher(Long.parseLong(id));
    }

    @GetMapping("/find/all")
    public List<Teacher> findAllTeachers() {
        return teacherService.findAllTeachers();
    }

    @PostMapping("/save")
    public Teacher saveTeacher(@RequestBody Teacher teacher) {
       return teacherService.saveTeacher(teacher);
    }

    @PostMapping("/update")
    public boolean updateTeacher(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    @PostMapping("/delete")
    public boolean deleteTeacher(@RequestBody Teacher teacher) {
        return teacherService.deleteTeacher(teacher);
    }
}
