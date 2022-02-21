package store.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Data
@Builder
@Entity
public class Employee{
    @Id
    private int id;
    @MapsId
    @OneToOne()
    private User user;
    @ManyToOne(targetEntity = Store.class)
    private Store store;
    private String eRole;
    private double salary;
}
