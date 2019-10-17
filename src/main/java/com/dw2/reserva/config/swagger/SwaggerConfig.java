package com.dw2.reserva.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
    private final String title;
    private final String description;
    private final String version;

    public SwaggerConfig(@Value("${info.app.name}") String title, @Value("${info.app.description}") String description,
                         @Value("${info.app.version}") String version) {
        this.title = title;
        this.description = description;
        this.version = version;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dw2.reserva.rest"))
                .build()
                .directModelSubstitute(LocalTime.class, String.class)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        final Contact contact = new Contact("Kainan Pereira Ramos",
                "",
                "kainanpr95@gmail.com");

        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .contact(contact)
                .build();
    }
}
