package fiap.Challenge.springsecurity.framework.repository;

import fiap.Challenge.springsecurity.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
        Optional<Role> findRoleByName(String roleName);

}
