package by.itacademy.javaenterprise.lepnikau.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    private Long id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private Long subjectId;

    private String subjectName;
}
