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

    @ManyToOne(targetEntity = Category.class)
    private Category category;
    private double price;
    private String description;

    @ManyToOne(targetEntity = Employee.class)
    private Employee addedBY;
    private Date addDate;

    @ElementCollection
    private List<Image> images = new ArrayList<>();

}
