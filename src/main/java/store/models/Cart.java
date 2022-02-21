package store.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;
    private Date buyDate;

    @ElementCollection
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product,Integer> products;
}
