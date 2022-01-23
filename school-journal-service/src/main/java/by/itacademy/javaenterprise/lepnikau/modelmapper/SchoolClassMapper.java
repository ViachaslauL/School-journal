package by.itacademy.javaenterprise.lepnikau.modelmapper;

import by.itacademy.javaenterprise.lepnikau.dto.SchoolClassDTO;
import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SchoolClassMapper {

    private final ModelMapper mapper;

    @Autowired
    public SchoolClassMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public SchoolClass toEntity(SchoolClassDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, SchoolClass.class);
    }

    public SchoolClassDTO toDto(SchoolClass entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, SchoolClassDTO.class);
    }
}
