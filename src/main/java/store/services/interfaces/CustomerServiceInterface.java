package store.services.interfaces;

import store.models.User;

import java.util.List;

public interface CustomerServiceInterface {

    List<User> topUsers(Integer count);
}
