package by.itacademy.javaenterprise.lepnikau.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParentDTO {

    private Long id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private Long studentId;
}
