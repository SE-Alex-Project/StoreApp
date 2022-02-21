package store.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    private String id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, targetEntity = Store.class)
    @JoinColumn
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store store;
//    private String eRole;
    private double salary;
}
