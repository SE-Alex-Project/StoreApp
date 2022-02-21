package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import store.models.Cart;
import store.models.Product;
import store.models.Store;
import store.models.User;
import store.repos.CartRepo;
import store.repos.StoreRepo;
import store.repos.UserRepo;
import store.services.interfaces.CartServiceInterface;

import java.beans.Transient;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService implements CartServiceInterface {

    @Autowired
    UserRepo userRepo;
    @Autowired
    StoreRepo storeRepo;
    @Autowired
    CartRepo cartRepo;

    @Override
    public Cart getCart(String userEmail) {
        return userRepo.getById(userEmail).getCart();
    }

    @Override
    @Transactional
    public void BuyCart(String userEmail, Integer StoreId) {
        Cart c = getCart(userEmail);
        Optional<Store> store = storeRepo.findById(StoreId);
        if (store.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Store found With Store Id: " + StoreId);
        Map<Product, Integer> storeProducts = store.get().getProducts();
        c.getProducts().forEach((product, quantity) -> {
            if (quantity <= storeProducts.get(product))
                storeProducts.put(product, storeProducts.get(product) - quantity);
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Enough product Quantity for product: " + product.getName());
        });
        Cart newCart = new Cart();
        c.getUser().setCart(newCart);
        newCart.setUser(c.getUser());
        userRepo.save(c.getUser());
    }

    @Override
    public void modifyCart(String userEmail, Map<Product, Integer> products) {
        Cart cart = getCart(userEmail);
        cart.setProducts(products);
        cartRepo.save(cart);
    }


    @Override
    public void addToCart(String userEmail, Map<Product,Integer> products) {
        Cart userCart = getCart(userEmail);
        products.forEach(userCart::addProduct);
        cartRepo.save(userCart);
    }
}
