package store.services.interfaces;

import store.models.Product;

import java.util.List;

public interface ProductService {
    Product add(String userEmail, Product product);
    Product edit(Product product);
    Product getById(int productId);
    List<Product> getAll();
    List<Product> search();
    void delete(int productId);
}
