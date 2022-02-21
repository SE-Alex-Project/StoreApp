package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import store.models.Cart;
import store.models.Product;
import store.models.Store;
import store.models.User;
import store.repos.CartRepo;
import store.repos.StoreRepo;
import store.repos.UserRepo;
import store.services.interfaces.CartServiceInterface;

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

    @Override// need to be in transaction
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
    public void modifyCart(Cart oldCart, Cart newCart) {

    }

    @Override
    public void addToCart(Cart cart, Product... products) {

    }
}
