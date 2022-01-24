package by.itacademy.javaenterprise.lepnikau.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "class_id")
    private Long classId;

    @OneToOne
    @JoinColumn(name = "class_id", insertable = false, updatable = false)
    private SchoolClass schoolClass;

    @Column(name = "subject_id")
    private Long subjectId;

    @OneToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private Subject subject;

    @Column(name = "task")
    private String task;

    @Column(name = "date")
    private Date date;
}
