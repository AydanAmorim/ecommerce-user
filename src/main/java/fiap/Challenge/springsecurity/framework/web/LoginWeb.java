package fiap.Challenge.springsecurity.framework.web;

import com.fasterxml.jackson.databind.JsonNode;
import fiap.Challenge.springsecurity.interfaceadapters.controllers.LoginController;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.login.LoginRequest;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.login.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class LoginWeb {
    @Resource
    private LoginController loginController;

    @PostMapping
    @Operation(summary = "Criar token")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(this.loginController.create(loginRequest));
    }

    @GetMapping(value = "/validate")
    @Operation(summary = "Valida token")
    public ResponseEntity<JsonNode> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(loginController.validateToken(authorizationHeader));
    }
}
