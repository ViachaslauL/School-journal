package by.itacademy.javaenterprise.lepnikau.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDTO {

    private Long subjectId;

    private String subjectName;

    private Set<TeacherDTO> teachers;
}
