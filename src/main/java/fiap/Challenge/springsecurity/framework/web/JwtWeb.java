package fiap.Challenge.springsecurity.framework.web;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
@RequestMapping(value = "/key")
public class JwtWeb {

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @GetMapping(value = "/.well-known/jwks.json")
    public Map<String, Object> getPublicKey() {
        RSAKey key = new RSAKey.Builder(publicKey).build();

        return new JWKSet(key).toJSONObject();
    }
}
