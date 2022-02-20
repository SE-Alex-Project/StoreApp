package store.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer{

    @Id
    private int ID;

    @OneToOne
    @MapsId
    private User user;

    @OneToOne(targetEntity = Cart.class)
    @JsonIgnore
    private Cart cart;

}
