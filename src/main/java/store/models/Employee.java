package store.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.List;


@Data
@Entity
public class Employee extends User{
    @ManyToOne(targetEntity = Store.class)
    private Store store;
    private String eRole;
    private double salary;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("EMPLOYEE"));
    }

}
