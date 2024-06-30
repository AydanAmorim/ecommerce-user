package fiap.Challenge.springsecurity.interfaceadapters.controllers;

import fiap.Challenge.springsecurity.entities.User;
import fiap.Challenge.springsecurity.interfaceadapters.gateway.UserGateway;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.UserPresenter;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.dto.UserDto;
import fiap.Challenge.springsecurity.util.pagination.PagedResponse;
import fiap.Challenge.springsecurity.util.pagination.Pagination;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class UserController {

    @Resource
    private UserGateway userGateway;

    @Resource
    private UserPresenter userPresenter;

    public PagedResponse<UserDto> findAll(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getPageSize());

        Page<User> users = this.userGateway.findAll(pageable);

        return this.userPresenter.convert(users);
    }




}
