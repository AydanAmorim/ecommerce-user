package fiap.Challenge.springsecurity.util;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiDocConfig(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Users Management System")
                                .description("Java Tech Challenge 5 project to implement an autenthication and authorization system")
                                .version("1.0.0")
                );
    }
}
