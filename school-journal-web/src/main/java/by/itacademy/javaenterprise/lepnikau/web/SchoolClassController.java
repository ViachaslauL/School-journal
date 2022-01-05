package by.itacademy.javaenterprise.lepnikau.web;

import by.itacademy.javaenterprise.lepnikau.dto.SchoolClassDTO;
import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
import by.itacademy.javaenterprise.lepnikau.service.SchoolClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/school_class")
public class SchoolClassController {

    private static final Logger LOG = LoggerFactory.getLogger(SchoolClassController.class);

    @Autowired
    private SchoolClassesService schoolClassesService;

    @GetMapping("/find")
    public SchoolClassDTO findSchoolClass(@RequestBody String schoolClassId) {
        SchoolClass schoolClass =
                schoolClassesService.findSchoolClass(Long.parseLong(schoolClassId));

        return SchoolClassDTO.builder()
                .id(schoolClass.getId())
                .number(schoolClass.getNumber())
                .letter(schoolClass.getLetter())
                .students(schoolClass.getStudents())
                .build();
    }

    @GetMapping("/find/all")
    public List<SchoolClassDTO> findAllSchoolClasses() {
        List<SchoolClassDTO> schoolClassDTOs = new ArrayList<>();

        for (SchoolClass schoolClass : schoolClassesService.findAllSchoolClasses()) {
            SchoolClassDTO schoolClassDTO = SchoolClassDTO.builder()
                    .id(schoolClass.getId())
                    .number(schoolClass.getNumber())
                    .letter(schoolClass.getLetter())
                    .students(schoolClass.getStudents())
                    .build();

            schoolClassDTOs.add(schoolClassDTO);
        }

        return schoolClassDTOs;
    }

    @PostMapping("/save")
    public SchoolClassDTO saveSchoolClass(@RequestBody SchoolClassDTO schoolClassDTO) {
        SchoolClass schoolClass = SchoolClass.builder()
                .number(schoolClassDTO.getNumber())
                .letter(schoolClassDTO.getLetter())
                .build();

        SchoolClass savedSchoolClass = schoolClassesService.saveSchoolClass(schoolClass);

        return SchoolClassDTO.builder()
                .id(savedSchoolClass.getId())
                .number(savedSchoolClass.getNumber())
                .letter(savedSchoolClass.getLetter())
                .build();
    }

    @PostMapping("/update")
    public boolean updateSchoolClass(@RequestBody SchoolClassDTO schoolClassDTO) {
        SchoolClass schoolClass = SchoolClass.builder()
                .id(schoolClassDTO.getId())
                .number(schoolClassDTO.getNumber())
                .letter(schoolClassDTO.getLetter())
                .build();

        return schoolClassesService.updateSchoolClass(schoolClass);
    }

    @PostMapping("/delete")
    public boolean deleteSchoolClass(@RequestBody SchoolClassDTO schoolClassDTO) {
        SchoolClass schoolClass = SchoolClass.builder()
                .id(schoolClassDTO.getId())
                .number(schoolClassDTO.getNumber())
                .letter(schoolClassDTO.getLetter())
                .build();

        return schoolClassesService.deleteSchoolClass(schoolClass);
    }
}
