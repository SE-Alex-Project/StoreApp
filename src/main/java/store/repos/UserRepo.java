package store.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import store.models.Role;
import store.models.User;

public interface UserRepo extends JpaRepository<User, String> {
    Role getRoleByEmail(String userEmail);
    @Query(
            nativeQuery = true,
            value = """
                    SELECT user_email,SUM(price * quantity) AS totalPrice
                    FROM Cart NATURAL JOIN cart_products NATURAL JOIN Product
                    WHERE buy_date >= DATE_ADD(NOW(),INTERVAL-90 DAY)
                    GROUP BY user_email
                    ORDER BY totalPrice DESC LIMIT :count ;"""
    )
    Object topUser(@Param("count") Integer count);
}
