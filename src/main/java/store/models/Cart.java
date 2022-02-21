package store.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date buyDate;

    @ElementCollection
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product,Integer> products = new HashMap<>();

    public void addProduct(Product product,Integer quantity){
        if(products.containsKey(product))
            products.put(product,products.get(product)+quantity);
        else
            products.put(product,quantity);
    }
}
