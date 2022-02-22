package store.services.interfaces;

import store.models.User;

public interface UserService {
    User handleUser(User user);
    User GetUserByEmail(String email);
    User UpdateUser(User user);
    void DeleteUserByEmail(String userEmail);
//    void LogOut(String email);
}
