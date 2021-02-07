package com.interview.tenpo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.MediaType.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    private final  String hostUrl;

    private final Contact DEFAULT_CONTACT = new Contact(
            "Mauricio Di Rienzo", "",
            "mauriciodirienzo@gmail.com");

    private final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Tenpo Test", "Tenpo API test", "1.0",
            "urn:tos", DEFAULT_CONTACT,
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());

    private final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<>(Arrays.asList(APPLICATION_JSON_VALUE,
                    APPLICATION_ATOM_XML_VALUE));

    public SwaggerConfig( @Value("${swagger.host.url}") String hostUrl) {
        this.hostUrl = hostUrl;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .apiInfo(DEFAULT_API_INFO)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.interview.tenpo")).build();
    }
}
