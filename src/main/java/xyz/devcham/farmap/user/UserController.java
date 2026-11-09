package xyz.devcham.farmap.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService svc;

    public UserController(UserService svc) {
        this.svc = svc;
    }

    @PostMapping("")
    public ResponseEntity<User> postUser(@Valid @RequestBody RequestCreateUser req) {
        User user = svc.createUser(req);
        return ResponseEntity.created(null).body(user);
    }
}
