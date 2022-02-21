package store.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
//@Table(name = "store" , uniqueConstraints = {
//        @UniqueConstraint(name = "Unique-columns" , columnNames = {"location","storeName"})
//})
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String location;
    private String storeName;
}
