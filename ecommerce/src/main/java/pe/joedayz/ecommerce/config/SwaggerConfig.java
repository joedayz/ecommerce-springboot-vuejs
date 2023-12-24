package pe.joedayz.ecommerce.config;

import java.util.List;
import org.aspectj.apache.bcel.classfile.Module.Open;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

  @Value("${joedayz.openapi.dev-url}")
  private String devUrl;

  @Value("${joedayz.openapi.prod-url}")
  private String prodUrl;
  @Bean
  public OpenAPI getApiInfo() {

    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment");

    Contact contact = new Contact();
    contact.setName("JoeDayz");
    contact.setUrl("https://joedayz.pe");
    contact.setEmail("jose.diaz@joedayz.pe");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("Ecommerce API")
        .description("Documentation Ecommerce api")
        .version("1.0.0")
        .license(mitLicense)
        .contact(contact);

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
  }
}
