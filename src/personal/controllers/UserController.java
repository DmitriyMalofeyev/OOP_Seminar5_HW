package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception {
        validateUserData(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        return repository.readUser(userId);
    }

    public List<User> readAllUsers() throws Exception{
        return repository.getAllUsers();
    }

    private void validateUserData(User user) {
        if ((user.getFirstName()).isEmpty() || (user.getLastName()).isEmpty() ||
                (user.getPhone()).isEmpty()) throw new IllegalStateException("Fields are empty");
    }

    public User updateUser(User user) throws Exception {
        return repository.updateUser(user);
    }
    public User deleteUser(User user) throws Exception {
        return repository.deleteUser(user);


    }
}
