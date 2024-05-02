package zw.co.zim.willplatform.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {
    private static final String AUTHOR_MAIL = "dev@willplatform.co.zw";
    private static final String API_TITLE = "WillPlatform API";
    private static final String API_DESCRIPTION = "WillPlatform API";
    private static final String VERSION = "1.0";

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title(API_TITLE)
                .contact(new Contact().email(AUTHOR_MAIL))
                .description(API_DESCRIPTION)
                .version(VERSION)
                .license(new License().name("WillPlatform Dev Team").url("#")))
            .externalDocs(new ExternalDocumentation().description("WillPlatform Documentation").url("#"));
    }
}
