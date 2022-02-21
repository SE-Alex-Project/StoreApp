package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.models.Cart;
import store.models.Role;
import store.models.User;
import store.services.interfaces.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User handleUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        Cart cart = new Cart();
        user.setCart(cart);
        cart.setUser(user);
        return user;
    }
}

