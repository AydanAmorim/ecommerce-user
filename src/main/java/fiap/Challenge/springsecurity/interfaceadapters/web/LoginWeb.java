package fiap.Challenge.springsecurity.interfaceadapters.web;

import fiap.Challenge.springsecurity.interfaceadapters.controllers.LoginController;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.login.LoginRequest;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.login.LoginResponse;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/login")
public class LoginWeb {
    @Resource
    private LoginController loginController;

    @PostMapping()
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(this.loginController.create(loginRequest));
    }
}
