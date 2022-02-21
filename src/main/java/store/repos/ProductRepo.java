package store.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;


public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query(
            nativeQuery = true,
            value = """
                    SELECT p.name ,p.price ,SUM(quantity) AS totalSales
                    FROM Cart NATURAL JOIN cart_products NATURAL JOIN Product AS p
                    WHERE buy_date >= DATE_ADD(NOW(),INTERVAL-90 DAY)
                    GROUP BY p.id
                    ORDER BY totalSales DESC LIMIT :count ;"""
    )
    List<Map<String, String>> topSales(@Param("count") Integer count);

    @Query(
            nativeQuery = true,
            value = """
                    SELECT p.name ,p.price ,SUM(quantity) AS totalSales
                    FROM Cart NATURAL JOIN cart_products NATURAL JOIN Product AS p
                    WHERE buy_date >= DATE_ADD(NOW(),INTERVAL-90 DAY)
                    GROUP BY p.id
                    ORDER BY totalSales DESC;"""
    )
    List<Map<String, String>> totalSales();
}
