package xyz.devcham.farmap.user;

import org.springframework.stereotype.Service;

import xyz.devcham.farmap.utils.Random;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User createUser(RequestCreateUser req) {
        User user = new User(
                Random.generateId(8),
                req.email(),
                req.password(),
                req.username());
        repo.insertUser(user);
        return user;
    }
}
