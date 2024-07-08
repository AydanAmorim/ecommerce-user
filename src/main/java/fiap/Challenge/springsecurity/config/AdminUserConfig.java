package fiap.Challenge.springsecurity.config;

import fiap.Challenge.springsecurity.entities.Role;
import fiap.Challenge.springsecurity.entities.User;
import fiap.Challenge.springsecurity.framework.repository.RoleRepository;
import fiap.Challenge.springsecurity.framework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Value("${SYSTEM_DEFAULT_USERNAME:admin}")
    private String DEFAULT_USERNAME;

    @Value("${SYSTEM_DEFAULT_PASSWORD:123}")
    private String DEFAULT_PASSWORD;

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    public AdminUserConfig(UserRepository userRepository
                            ,RoleRepository roleRepository
                            ,BCryptPasswordEncoder passEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passEncoder;
    }
    @Override
    @Transactional
    public void run(String... args){
        var roleAdmin = roleRepository.findRoleByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findUserByUsername(DEFAULT_USERNAME);

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Usuário " +DEFAULT_USERNAME + " já existe! nada será alterado!");
                },
                () -> {
                    var userNewAdmin = new User();
                    userNewAdmin.setUsername(DEFAULT_USERNAME);
                    userNewAdmin.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
                    userNewAdmin.setRoles(Set.of(roleAdmin));
                    userRepository.save(userNewAdmin);
                }
        );

    }
}
