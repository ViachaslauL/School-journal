package by.itacademy.javaenterprise.lepnikau.modelmapper;

import by.itacademy.javaenterprise.lepnikau.dto.ParentDTO;
import by.itacademy.javaenterprise.lepnikau.dto.StudentDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Parent;
import by.itacademy.javaenterprise.lepnikau.entity.Student;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class StudentMapper {

    private final ModelMapper mapper;
    private final ParentMapper parentMapper;

    @Autowired
    public StudentMapper(ModelMapper mapper, ParentMapper parentMapper) {
        this.mapper = mapper;
        this.parentMapper = parentMapper;
    }

    @PostConstruct
    private void setupMapper () {
        mapper.createTypeMap(StudentDTO.class, Student.class)
                .addMappings(m -> m.skip(Student::setParents)).setPostConverter(toEntityConverter());
    }

    public Student toEntity(StudentDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Student.class);
    }

    public StudentDTO toDto(Student entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, StudentDTO.class);
    }

    private Converter<StudentDTO, Student> toEntityConverter() {
        return context -> {
            StudentDTO source = context.getSource();
            Student destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(StudentDTO source, Student destination) {
        Set<Parent> parents = new LinkedHashSet<>();

        if (source.getParents() != null && !source.getParents().isEmpty()) {
            for (ParentDTO parent : source.getParents()) {
                parents.add(parentMapper.toEntity(parent));
            }
        } else {
            destination.setParents(new LinkedHashSet<>());
        }
        destination.setParents(parents);
    }
}
