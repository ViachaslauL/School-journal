package by.itacademy.javaenterprise.lepnikau.entity;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Table(name = "marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student studentId;

    @Column(name = "mark")
    private int mark;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subjectId;

    @Column(name = "date")
    private Date date;

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", mark=" + mark +
                ", subjectId=" + subjectId +
                ", date=" + date +
                '}';
    }
}
