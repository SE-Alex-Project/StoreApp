package store.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import store.models.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
//    Boolean existsByEmail(String Email);
//    Optional<User> findByEmail(String username);
//    User getByEmail(String email);
}
