package store.repos;

import store.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Integer> {
    List<Cart> findByUser(String userEmail);
}
