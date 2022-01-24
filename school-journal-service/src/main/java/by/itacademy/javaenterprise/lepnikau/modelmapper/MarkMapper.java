package by.itacademy.javaenterprise.lepnikau.modelmapper;

import by.itacademy.javaenterprise.lepnikau.dto.MarkDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Mark;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class MarkMapper {

    private final ModelMapper mapper;

    @Autowired
    public MarkMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Mark.class, MarkDTO.class)
                .addMappings(m -> m.skip(MarkDTO::setStudentId))
                .setPostConverter(toDtoConverter());
    }

    public Mark toEntity(MarkDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Mark.class);
    }

    public MarkDTO toDto(Mark entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, MarkDTO.class);
    }

    private Converter<Mark, MarkDTO> toDtoConverter() {
        return context -> {
            MarkDTO destination = context.getDestination();
            mapSpecificFieldStudentId(destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFieldStudentId(MarkDTO destination) {
        destination.setStudentId(null);
        destination.setSubjectId(null);
    }
}
