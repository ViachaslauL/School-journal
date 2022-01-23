package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.StudentDTO;
import by.itacademy.javaenterprise.lepnikau.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Set<StudentDTO> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO findStudent(@PathVariable String id) {
        return studentService.findStudent(Long.parseLong(id));
    }

    @PostMapping("/save")
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    @PostMapping("/update")
    public boolean updateStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(studentDTO);
    }

    @PostMapping("/delete")
    public boolean deleteStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.deleteStudent(studentDTO);
    }

}
