package mate.academy.rickandmorty.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI myOpenApi() {
        Info info = new Info()
                .title("Rick And Morty Management API")
                .description("This API exposes endpoints to manage Rick And Morty API.")
                .version("ver: 1.0");

        return new OpenAPI().info(info);
    }
}
