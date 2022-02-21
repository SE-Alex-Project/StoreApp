package store.models;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    private Category category;
    private double price;
    private String description;

    @ManyToOne
    private Employee addedBY;
    private Date addDate;

    @ElementCollection
    private List<Image> images = new ArrayList<>();

}
