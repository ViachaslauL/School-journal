package by.itacademy.javaenterprise.lepnikau.dto;

import by.itacademy.javaenterprise.lepnikau.entity.Student;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkDTO {

    private Long id;

    private Long studentId;

    private Student student;

    private int mark;

    private Long subjectId;

    private Subject subject;

    private Date date;
}
