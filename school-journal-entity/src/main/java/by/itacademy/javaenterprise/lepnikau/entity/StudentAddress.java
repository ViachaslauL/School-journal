package by.itacademy.javaenterprise.lepnikau.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Embeddable
@Access(AccessType.PROPERTY)
public class StudentAddress {

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private Integer houseNumber;

    @Column(name = "flat_number")
    private Integer flatNumber;
}
