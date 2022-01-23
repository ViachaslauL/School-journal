package by.itacademy.javaenterprise.lepnikau.dto;

import by.itacademy.javaenterprise.lepnikau.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;

    private String lastName;

    private String firstName;

    private String patronymic;

    private Long classId;

    private List<ParentDTO> parents;

    private Address address;

}
