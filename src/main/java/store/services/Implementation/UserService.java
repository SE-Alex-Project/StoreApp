package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import store.models.User;
import store.repos.UserRepo;
import store.services.interfaces.UserServiceInterface;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService, UserServiceInterface {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(username);
        if (user.isPresent())
            return user.get();
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
