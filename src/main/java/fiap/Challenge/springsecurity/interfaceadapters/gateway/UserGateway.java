package fiap.Challenge.springsecurity.interfaceadapters.gateway;


import fiap.Challenge.springsecurity.entities.User;
import fiap.Challenge.springsecurity.framework.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserGateway {
    @Resource
    private UserRepository userRepository;

    public Page<User> findAll(Pageable pageable){
        return this.userRepository.findAll(pageable);
    }

    public Optional<User> findUserByUsername(String username){
        return this.userRepository.findUserByUsername(username);
    }

    public User insert(User user){
        return this.userRepository.save(user);
    }


}
