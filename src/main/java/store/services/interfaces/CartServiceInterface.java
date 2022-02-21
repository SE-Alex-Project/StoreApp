package store.services.interfaces;

import store.models.Cart;
import store.models.Product;

import java.util.Map;

public interface CartServiceInterface {

    Cart getCart(String userEmail);

    //return new Saved Cart
    void BuyCart(String userEmail,Integer StoreId);

    void modifyCart(String userEmail, Map<Product,Integer> products);

    void addToCart(String userEmail, Map<Product,Integer> products);


}
