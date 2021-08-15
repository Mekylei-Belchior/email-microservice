package br.com.mekyei.ms.email.configurations.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
public class SwaggerConfigurations {

    /**
     * Configura quais endpoints e pacotes o Swagger deve gerar a documentação.
     *
     * @return o parâmetro criado
     */
    @Bean
    public Docket docket() {

        /*
         * Cria o objeto Docket
         */

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .tags(
                        new Tag("Autenticação", "Endpoint de autenticação"),
                        new Tag("E-mail", "Endpoint de envio de e-mails")
                )
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .securitySchemes(Arrays.asList(apiKey()))
                .forCodeGeneration(true);

    }

    /* Token que autentica o cliente. */
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Token", "header");
    }

    /*
     * Descreve as informações gerais.
     */
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("E-mail Microservice")
                .description("Microserviço para envio de e-mails")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0.0")
                .contact(new Contact(
                        "Mekylei Belchior",
                        "https://www.linkedin.com/in/mekylei-belchior-89794889/",
                        "mekylei@msn.com")
                )
                .build();
    }
}
