package by.itacademy.javaenterprise.lepnikau.dto;

import by.itacademy.javaenterprise.lepnikau.entity.SchoolClass;
import by.itacademy.javaenterprise.lepnikau.entity.Subject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties(value = {"scheduleId", "classId", "subjectId"}, allowSetters = true)
public class ScheduleDTO {

    @JsonProperty("scheduleId")
    private Long scheduleId;
    @JsonProperty("classId")
    private Long classId;

    private SchoolClass schoolClass;
    @JsonProperty("subjectId")
    private Long subjectId;

    private Subject subject;

    private String task;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd:HH-mm")
    private Date date;
}
