package br.com.CastGroup.ValidadorArquivo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SuaggerConfig {

    @Bean
    public Docket docket(){
return  new Docket(DocumentationType.SWAGGER_2)
    .useDefaultResponseMessages(false)
        .select()
    .apis(RequestHandlerSelectors.basePackage("br.com.CastGroup.ValidadorArquivo.Controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                .title("")
                .description("")
                .version("")
                .contact(contact())
                .build();
    }

    private springfox.documentation.service.Contact contact(){
        return  new springfox.documentation.service.Contact("Tiago Henrique dos Santos",
                "https://github.com/Thenrique395/CastGroup",
                "thenrique395@gmail.com");

    }






}


