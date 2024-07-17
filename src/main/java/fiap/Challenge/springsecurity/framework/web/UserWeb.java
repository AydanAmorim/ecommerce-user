package fiap.Challenge.springsecurity.framework.web;
import fiap.Challenge.springsecurity.interfaceadapters.controllers.UserController;
import fiap.Challenge.springsecurity.interfaceadapters.presenters.dto.UserDto;
import fiap.Challenge.springsecurity.util.pagination.PagedResponse;
import fiap.Challenge.springsecurity.util.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/users")
@Tag(name ="User management route")
public class UserWeb {
    @Resource
    private UserController userController;

    @Operation(summary = "Get all Users Information")
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<PagedResponse<UserDto>> findAll(@Parameter(description = "Default value 10. Max value 1000", example = "10") @RequestParam(required = false) Integer pageSize,
                                                          @Parameter(description = "Default value 0", example = "0") @RequestParam(required = false) Integer initialPage) {

        Pagination page = new Pagination(initialPage, pageSize);
        return ResponseEntity.ok(this.userController.findAll(page));
    }

    @Operation(summary="Add a new user")
    @PostMapping(value = "/basic")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto userSavedDto = this.userController.insertBasicUser(userDto);
        return ResponseEntity.ok(userSavedDto);
    }

    @Operation(summary="Add a new ADMIN user")
    @PostMapping(value = "/admin")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<UserDto> addAdminUser(@RequestBody UserDto userDto) {
        UserDto userSavedDto = this.userController.insertAdminUser(userDto);
        return ResponseEntity.ok(userSavedDto);
    }

}
