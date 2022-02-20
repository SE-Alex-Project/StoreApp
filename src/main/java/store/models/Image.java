package store.models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Image {
    @Id
    private String Url;
}
