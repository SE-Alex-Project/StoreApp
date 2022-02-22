package store.services.interfaces;

import store.models.Cart;
import store.models.Product;

import java.util.Map;

public interface CartService {

    Cart getCart(String userEmail);

    void buyCart(String userEmail,Integer StoreId);

    void modifyCart(String userEmail, Map<Product,Integer> products);

    void addToCart(String userEmail, Map<Product,Integer> products);


}
