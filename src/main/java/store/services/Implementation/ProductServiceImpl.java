package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import store.models.Product;
import store.repos.ProductRepo;
import store.services.interfaces.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Override
    public Product add(String userEmail, Product product) {
        if (productRepo.existsById(product.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Already Exists!");

        return productRepo.save(product);
    }

    @Override
    public Product edit(Product product) {
        if (!productRepo.existsById(product.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Doesn't Exist!");

        return productRepo.save(product);
    }

    @Override
    public Product getById(int productId) {
        return productRepo
                .findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Doesn't Exist!"));
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> search() {
        // TODO
        return null;
    }

    @Override
    public void delete(int productId) {
        productRepo.deleteById(productId);
    }

}
