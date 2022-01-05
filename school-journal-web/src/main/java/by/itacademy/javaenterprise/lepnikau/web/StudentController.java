package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.entity.Student;
import by.itacademy.javaenterprise.lepnikau.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/find")
    public Student findStudent(@RequestBody String id) {
        return studentService.findStudent(id);
    }

    @GetMapping("/find/all")
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PostMapping("/update")
    public boolean updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @PostMapping("/delete")
    public boolean deleteStudent(@RequestBody Student student) {
        return studentService.deleteStudent(student);
    }

}
