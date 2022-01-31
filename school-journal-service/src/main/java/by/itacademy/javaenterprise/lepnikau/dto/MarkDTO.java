package by.itacademy.javaenterprise.lepnikau.dto;

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
@JsonIgnoreProperties(value = {"studentId", "subjectId"}, allowSetters = true)
public class MarkDTO {

    private Long id;
    @JsonProperty("studentId")
    private Long studentId;

    private StudentDTO student;

    private int mark;
    @JsonProperty("subjectId")
    private Long subjectId;

    private SubjectDTO subject;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd:HH-mm")
    private Date date;
}
