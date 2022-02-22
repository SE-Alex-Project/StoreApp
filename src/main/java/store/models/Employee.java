package store.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Employee {
    @Id
    @JsonIgnore
    private String id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Store.class)
    @JoinColumn
    private Store store;

//    private String eRole;
    private double salary;
}
