package store.models;


import lombok.Data;

import javax.persistence.*;
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
    @JoinColumn(
            name ="category_name",
            referencedColumnName = "categoryName"
    )
    private Category categoryName;
    private double price;
    private String Description;

    @ManyToOne(targetEntity = Employee.class)
    private Employee addedBY;
    private Date addDate;

}
