package by.itacademy.javaenterprise.lepnikau.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchoolClassDTO {

    private Long id;

    private int number;

    private String letter;

    private String classCode;

    private List<StudentDTO> students;

}
