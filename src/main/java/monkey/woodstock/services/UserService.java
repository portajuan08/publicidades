package monkey.woodstock.services;


import monkey.woodstock.domain.User;

public interface UserService {
    Iterable<User> listAllUsers();

    User getUserByName(String name);

    User saveUser(User user);
    
    void deleteUser(User user);
}

