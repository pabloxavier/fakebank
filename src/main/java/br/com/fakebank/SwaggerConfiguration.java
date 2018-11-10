package br.com.fakebank;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket apiDoc() {
    
        List<ResponseMessage> defaultResponseMessages =
            Lists.newArrayList(
                new ResponseMessageBuilder()
                    .code(401)
                    .message("Recurso não autorizado para acesso")
                    .build(),
                new ResponseMessageBuilder()
                    .code(403)
                    .message("Acesso negado")
                    .build(),
                new ResponseMessageBuilder()
                    .code(500)
                    .message("Failed to process request")
                    .build());

        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(Predicates.not(PathSelectors.regex("/error")))
            .build()
            .apiInfo(metaData())
            .globalResponseMessage(RequestMethod.GET, defaultResponseMessages)
            .globalResponseMessage(RequestMethod.POST, defaultResponseMessages)
            .globalResponseMessage(RequestMethod.PUT, defaultResponseMessages)
            .globalResponseMessage(RequestMethod.DELETE, defaultResponseMessages);
    }
    
    private ApiInfo metaData() {
        ApiInfo informacoesApiSwagger = new ApiInfo(
            "API do Fakebank",
            "Fakebank API para consumo da aplicação.",
            "1.0",
            "Termos de serviço",
            new Contact("Equipe UC", "", ""),
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<VendorExtension>());
            
        return informacoesApiSwagger;
    }
}
