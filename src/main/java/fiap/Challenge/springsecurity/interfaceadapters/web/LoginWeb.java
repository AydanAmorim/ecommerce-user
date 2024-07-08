package fiap.Challenge.springsecurity.interfaceadapters.web;


import fiap.Challenge.springsecurity.entities.Role;
import fiap.Challenge.springsecurity.interfaceadapters.gateway.UserGateway;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.login.LoginRequest;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.login.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/login")
public class LoginWeb {

    @Value("${spring.application.name}")
    private String DEFAULT_SYSNAME;

    private JwtEncoder jwtEncoder;
    private BCryptPasswordEncoder passwordEncoder;
    private UserGateway userGateway;



    @PostMapping()
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        var user = userGateway.findUserByUsername(loginRequest.username());

        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)){
            throw new BadCredentialsException("Usuário ou senha são inválidos, verifique suas credenciais!");
        }

        var actualDateTime = Instant.now();
        var expiresTokenInSeconds = 300L;

        var scopes = user.get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer(DEFAULT_SYSNAME)
                .subject(user.get().getId().toString())
                .issuedAt(actualDateTime)
                .expiresAt(actualDateTime.plusSeconds(expiresTokenInSeconds))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresTokenInSeconds));

    }



}
