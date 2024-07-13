package fiap.Challenge.springsecurity.interfaceadapters.gateway;

import fiap.Challenge.springsecurity.entities.Role;
import fiap.Challenge.springsecurity.framework.repository.RoleRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RoleGateway {
    @Resource
    private RoleRepository roleRepository;

    public Role findRoleByName(String roleName){
        return this.roleRepository.findRoleByName(roleName);
    }

}
