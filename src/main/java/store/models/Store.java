package store.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String location;
    private String name;

    @ElementCollection
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product,Integer> products = new HashMap<>();
}
