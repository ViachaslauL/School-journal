package by.itacademy.javaenterprise.lepnikau.modelmapper;

import by.itacademy.javaenterprise.lepnikau.dto.SubjectDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SubjectMapper {

    private final ModelMapper mapper;

    @Autowired
    public SubjectMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Subject toEntity(SubjectDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Subject.class);
    }

    public SubjectDTO toDto(Subject entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, SubjectDTO.class);
    }
}
