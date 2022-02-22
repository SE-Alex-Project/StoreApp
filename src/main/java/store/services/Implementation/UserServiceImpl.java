package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import store.models.Cart;
import store.models.Role;
import store.models.User;
import store.repos.UserRepo;
import store.services.interfaces.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findById(username);
        if (user.isPresent())
            return user.get();
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    @Override
    public User handleUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        Cart cart = new Cart();
        user.setCart(cart);
        cart.setUser(user);
        return user;
    }

    @Override
    public User GetUserByEmail(String email) {
        return userRepo.findById(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Doesn't Exist!"));
    }


    // need to be modified.
    @Override
    public User UpdateUser(User user) {
        User SaveUser =GetUserByEmail(user.getEmail());
        SaveUser.setFName(user.getFName());
        SaveUser.setLName(user.getLName());
        SaveUser.setPassword(user.getPassword()); // need to encode the password here.
        SaveUser.setConfirmPassword(user.getConfirmPassword());
        return userRepo.save(SaveUser);
    }

    @Override
    public void DeleteUserByEmail(String userEmail) {
        userRepo.deleteById(userEmail);;
    }

//    @Override
//    public void LogOut(String email) {
//        userRepo.deleteByEmail(email);
//    }
}

