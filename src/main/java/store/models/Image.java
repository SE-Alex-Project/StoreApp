package store.models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;


@Data
@Entity
public class Image {
    @Id
    private String Url;

    @ManyToOne(targetEntity = Product.class)
    private Product product;
}
