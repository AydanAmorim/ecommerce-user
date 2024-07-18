package fiap.Challenge.springsecurity.util.configs.documentation;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.gateway.address}")
    private String gateway;

    String schemeName = "bearerAuth";

    String bearerFormat = "JWT";

    String scheme = "bearer";

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(schemeName)).components(new Components()
                        .addSecuritySchemes(
                                schemeName, new SecurityScheme()
                                        .name(schemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .bearerFormat(bearerFormat)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme(scheme)
                        )
                ).addServersItem(new Server().url(gateway))
                .info(
                        new Info()
                                .title("Users Management System")
                                .description("Java Tech Challenge 5 project to implement an autenthication and authorization system")
                                .version("1.0.0")
                );
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("Usuário")
                .packagesToScan("fiap.Challenge.springsecurity.framework.web")
                .build();
    }
}
