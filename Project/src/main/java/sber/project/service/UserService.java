package sber.project.service;

import sber.project.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();

    Optional<User> getUserByEmail(String name);

    void createOrUpdateUser(User user);

    void deleteUserById(int id);
}