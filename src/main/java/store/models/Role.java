package store.models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Role {
    @Id
    String RoleName;
}

