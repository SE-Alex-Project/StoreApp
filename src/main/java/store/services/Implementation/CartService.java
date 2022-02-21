package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.models.Cart;
import store.models.Product;
import store.models.User;
import store.repos.UserRepo;
import store.services.interfaces.CartServiceInterface;

@Service
public class CartService implements CartServiceInterface {

    @Autowired
    UserRepo userRepo;

    @Override
    public Cart getCart(String userEmail) {
        return userRepo.getById(userEmail).getCart();
    }

    @Override
    public Cart BuyCart(String userEmail) {
        Cart c = getCart(userEmail);
        return null;
    }

    @Override
    public void modifyCart(Cart oldCart, Cart newCart) {

    }

    @Override
    public void addToCart(Cart cart, Product... products) {

    }
}
