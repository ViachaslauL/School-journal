package by.itacademy.javaenterprise.lepnikau.dto;

import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleDTO {

    private Long scheduleId;

    private Long classId;

    private SchoolClass schoolClass;

    private Long subjectId;

    private Subject subject;

    private String task;

    private Date date;
}
