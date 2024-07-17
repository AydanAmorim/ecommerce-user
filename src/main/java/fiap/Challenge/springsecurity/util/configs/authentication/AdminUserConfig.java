package fiap.Challenge.springsecurity.util.configs.authentication;

import fiap.Challenge.springsecurity.entities.Role;
import fiap.Challenge.springsecurity.entities.User;
import fiap.Challenge.springsecurity.framework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {
    @Value("${SYSTEM_DEFAULT_USERNAME:admin}")
    private String DEFAULT_USERNAME;

    @Value("${SYSTEM_DEFAULT_PASSWORD:123}")
    private String DEFAULT_PASSWORD;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(UserRepository userRepository, BCryptPasswordEncoder passEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Optional<User> userAdmin = userRepository.findUserByUsername(DEFAULT_USERNAME);

        if (userAdmin.isEmpty()) {
            User userNewAdmin = new User();
            userNewAdmin.setUsername(DEFAULT_USERNAME);
            userNewAdmin.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));

            Role role = new Role();
            role.setType(Role.Values.ADMIN);

            userNewAdmin.setRoles(Set.of(role));

            userRepository.save(userNewAdmin);
        }
    }
}
