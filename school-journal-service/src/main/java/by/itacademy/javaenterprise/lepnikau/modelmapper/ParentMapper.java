package by.itacademy.javaenterprise.lepnikau.modelmapper;

import by.itacademy.javaenterprise.lepnikau.dto.ParentDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Parent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ParentMapper {

    private final ModelMapper mapper;

    @Autowired
    public ParentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Parent toEntity(ParentDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Parent.class);
    }

    public ParentDTO toDto(Parent entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ParentDTO.class);
    }
}
