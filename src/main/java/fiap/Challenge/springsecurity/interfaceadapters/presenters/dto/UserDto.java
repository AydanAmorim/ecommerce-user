package fiap.Challenge.springsecurity.interfaceadapters.presenters.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends Dto{

    @NotEmpty
    @Schema(example = "name.lastName")
    private String username;

    @NotEmpty
    @Schema(example = "Secure password prefer to use symbols, letters and numbers")
    private String password;
}
