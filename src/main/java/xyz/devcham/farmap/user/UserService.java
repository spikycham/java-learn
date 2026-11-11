package xyz.devcham.farmap.user;

import java.time.Duration;

import org.springframework.stereotype.Service;

import xyz.devcham.farmap.utils.CacheService;
import xyz.devcham.farmap.utils.Random;

class ResultLogin {
    private ResponseUser user;
    private String sessionId;

    public ResultLogin(ResponseUser user, String sessionId) {
        this.user = user;
        this.sessionId = sessionId;
    }

    public ResponseUser getUser() {
        return user;
    }

    public String getSessionId() {
        return sessionId;
    }
}

@Service
public class UserService {
    private final UserRepository repo;
    private final CacheService cache;

    public UserService(UserRepository repo, CacheService cache) {
        this.repo = repo;
        this.cache = cache;
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

    public ResultLogin login(RequestLogin req) {
        User user = repo.getUserByEmailAndPassword(req.email(), req.password());
        if (user == null)
            return null;

        // Create a session id with expiration time.
        String sessionId = Random.generateId(8);
        cache.put("session:" + sessionId, user.getUsername(), Duration.ofMinutes(1));

        ResponseUser ru = new ResponseUser(user.getId(), user.getUsername(), user.getEmail());
        return new ResultLogin(ru, sessionId);
    }

    public ResponseUser readMe(String userId) {
        User user = repo.getUserById(userId);
        if (user == null)
            return null;
        return new ResponseUser(user.getId(), user.getUsername(), user.getEmail());
    }
}
