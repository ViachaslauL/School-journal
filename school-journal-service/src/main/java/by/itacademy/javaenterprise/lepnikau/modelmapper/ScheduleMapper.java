package by.itacademy.javaenterprise.lepnikau.modelmapper;

import by.itacademy.javaenterprise.lepnikau.dto.ScheduleDTO;
import by.itacademy.javaenterprise.lepnikau.entity.Schedule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ScheduleMapper {

    private final ModelMapper mapper;

    @Autowired
    public ScheduleMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Schedule toEntity(ScheduleDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Schedule.class);
    }

    public ScheduleDTO toDto(Schedule entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ScheduleDTO.class);
    }
}
