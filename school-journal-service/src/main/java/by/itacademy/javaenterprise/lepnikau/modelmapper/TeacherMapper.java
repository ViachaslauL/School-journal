package by.itacademy.javaenterprise.lepnikau.modelmapper;

import by.itacademy.javaenterprise.lepnikau.dto.TeacherDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TeacherMapper {

    private final ModelMapper mapper;

    @Autowired
    public TeacherMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Teacher toEntity(TeacherDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Teacher.class);
    }

    public TeacherDTO toDto(Teacher entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, TeacherDTO.class);
    }
}
