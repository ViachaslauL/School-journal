package by.itacademy.javaenterprise.lepnikau.entity;

import lombok.*;

import javax.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @Column(name = "mark")
    private int mark;

    @Column(name = "subject_id")
    private Long subjectId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private Subject subject;

    @Column(name = "date")
    private Date date;
}
