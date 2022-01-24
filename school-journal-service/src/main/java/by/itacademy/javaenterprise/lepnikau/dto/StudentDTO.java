package by.itacademy.javaenterprise.lepnikau.dto;

import by.itacademy.javaenterprise.lepnikau.entity.Address;
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
public class StudentDTO {

    private Long id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private Long classId;

    private Set<ParentDTO> parents;

    private Address address;

}
