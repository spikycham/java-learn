package xyz.devcham.farmap.user;

import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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
        ResultLogin res = svc.login(req);
        if (res == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        ResponseCookie cookie = ResponseCookie.from("SESSION_ID", res.getSessionId())
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofMinutes(1))
                .build();

        return ResponseEntity.created(null).header("Set-Cookie", cookie.toString()).body(res.getUser());
    }

    @GetMapping("/me/{user_id}")
    public ResponseEntity<ResponseUser> getMe(@PathVariable("user_id") String userId) {
        ResponseUser ru = svc.readMe(userId);
        if (ru == null)
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(ru);
    }
}
