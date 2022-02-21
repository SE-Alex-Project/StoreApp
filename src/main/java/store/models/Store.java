package store.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String location;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String name;

    @ElementCollection
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Map<Product,Integer> products = new HashMap<>();
}
