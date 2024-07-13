package fiap.Challenge.springsecurity.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "IdUser"),
            inverseJoinColumns = @JoinColumn(name = "IdRole")
    )
    private Set<Role> roles;

    public boolean isLoginCorrect(String passwordInform, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(passwordInform, this.password);
    }
}