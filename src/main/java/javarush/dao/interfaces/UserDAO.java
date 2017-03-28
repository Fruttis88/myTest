package javarush.dao.interfaces;

import javarush.entities.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();
    List<User> getUsers(String name);
    void saveUser(String name, int age, boolean isAdmin);
    void deleteUser(User user);
    void updateUser(User user);
}
