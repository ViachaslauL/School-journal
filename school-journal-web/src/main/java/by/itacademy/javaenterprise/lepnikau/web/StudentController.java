package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.StudentDTO;
import by.itacademy.javaenterprise.lepnikau.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Set<StudentDTO> findAllStudents(
            @RequestParam("pNumber") int pNumber,
            @RequestParam("pSize") int pSize
    ) {
        return studentService.findAllStudents(pNumber, pSize);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public StudentDTO findStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public boolean updateStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(studentDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.deleteStudent(studentDTO);
    }

}
