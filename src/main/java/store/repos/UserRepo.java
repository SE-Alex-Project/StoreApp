package store.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import store.models.User;

public interface UserRepo extends JpaRepository<User, String> {
}
