package store.services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.models.User;
import store.repos.UserRepo;
import store.services.interfaces.CustomerServiceInterface;

import java.util.List;

@Service
public class CustomerService implements CustomerServiceInterface {
    @Autowired
    UserRepo userRepo;

    @Override
    public List<User> topUsers(Integer count) {
        return null;
    }
}
