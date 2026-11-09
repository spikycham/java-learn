package xyz.devcham.farmap.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<ResponseUser> postUser(@Valid @RequestBody RequestCreateUser req) {
        return ResponseEntity.created(null).body(svc.createUser(req));
    }

    @PostMapping("/session")
    public ResponseEntity<ResponseUser> postSession(@Valid @RequestBody RequestLogin req) {
        ResponseUser ru = svc.login(req);
        if (ru == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.created(null).body(ru);
    }

    @GetMapping("/me/{user_id}")
    public ResponseEntity<ResponseUser> getMe(@PathVariable("user_id") String userId) {
        ResponseUser ru = svc.readMe(userId);
        if (ru == null)
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(ru);
    }
}
