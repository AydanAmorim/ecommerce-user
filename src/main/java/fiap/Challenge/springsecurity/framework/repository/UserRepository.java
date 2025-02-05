package fiap.Challenge.springsecurity.framework.repository;

import fiap.Challenge.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String name);

}
