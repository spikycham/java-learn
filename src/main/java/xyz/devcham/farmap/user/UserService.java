package xyz.devcham.farmap.user;

import org.springframework.stereotype.Service;

import xyz.devcham.farmap.utils.Random;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public ResponseUser createUser(RequestCreateUser req) {
        User user = new User(
                Random.generateId(8),
                req.email(),
                req.password(),
                req.username());
        repo.insertUser(user);

        ResponseUser ru = new ResponseUser(user.getId(), user.getUsername(), user.getEmail());
        return ru;
    }

    public ResponseUser login(RequestLogin req) {
        User user = repo.getUserByEmailAndPassword(req.email(), req.password());
        // TODO: need to create a session id and insert into redis with expiration time.
        if (user == null)
            return null;
        return new ResponseUser(user.getId(), user.getUsername(), user.getEmail());
    }

    public ResponseUser readMe(String userId) {
        User user = repo.getUserById(userId);
        if (user == null)
            return null;
        return new ResponseUser(user.getId(), user.getUsername(), user.getEmail());
    }
}
