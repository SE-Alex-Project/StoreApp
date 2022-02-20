package store.repos;

import store.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, String> {
    boolean existsByEmail(String email);
}
