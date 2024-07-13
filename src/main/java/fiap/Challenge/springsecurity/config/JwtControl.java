package fiap.Challenge.springsecurity.config;

import fiap.Challenge.springsecurity.entities.Role;
import fiap.Challenge.springsecurity.interfaceadapters.gateway.UserGateway;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.login.LoginRequest;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.login.LoginResponse;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class JwtControl {
    @Value("${spring.application.name}")
    private String DEFAULT_SYSNAME;

    @Resource
    private UserGateway userGateway;

    @Resource
    private JwtEncoder jwtEncoder;

    public LoginResponse createTokenJWT(LoginRequest loginRequest){
        var user  = this.userGateway.findUserByUsername(loginRequest.username());

        var actualDateTime = Instant.now();
        var expiresTokenInSeconds = 300L;

        var scopes = user.get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer(this.DEFAULT_SYSNAME)
                .subject(user.get().getId().toString())
                .issuedAt(actualDateTime)
                .expiresAt(actualDateTime.plusSeconds(expiresTokenInSeconds))
                .claim("scope", scopes)
                .build();

        var jwtValue = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresTokenInSeconds);

    }
}
