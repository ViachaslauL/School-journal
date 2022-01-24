package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.StudentDTO;
import by.itacademy.javaenterprise.lepnikau.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<StudentDTO> findAllStudents(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return studentService.findAllStudents(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public StudentDTO findStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public boolean updateStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(studentDTO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.deleteStudent(studentDTO);
    }

}
