package by.itacademy.javaenterprise.lepnikau.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentAddress {

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private Integer houseNumber;

    @Column(name = "flat_number")
    private Integer flatNumber;

    @Override
    public String toString() {
        return "StudentAddress{" +
                "street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", flatNumber=" + flatNumber +
                '}';
    }
}
