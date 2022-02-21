package store.services.interfaces;

import store.models.Cart;
import store.models.Product;

public interface CartServiceInterface {

    Cart getCart(String userEmail);

    //return new Saved Cart
    Cart BuyCart(String userEmail);

    void modifyCart(Cart oldCart , Cart newCart);

    void addToCart(Cart cart,Product... products);


}
