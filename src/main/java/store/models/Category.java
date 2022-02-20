package store.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Category {
    @Id
    @NotBlank(message="Category Name is required")
    private String categoryName;
}
