package store.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne(targetEntity = Customer.class)
    @NotNull
    private Customer customer;
    private Date buyDate;

    @OneToMany(targetEntity = Product.class)
    private List<Product> products;
}
