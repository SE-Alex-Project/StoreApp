package store.services.interfaces;

import store.models.User;

import java.util.List;

public interface CustomerService {

    List<User> topUsers(Integer count);
}
