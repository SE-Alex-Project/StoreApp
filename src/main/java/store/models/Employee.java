package store.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    private int id;
    @MapsId
    @OneToOne()
    private User user;
    @ManyToOne(targetEntity = Store.class)
    private Store store;
//    private String eRole;
    private double salary;
}
