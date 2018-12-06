package com.liwh.config;

import com.liwh.domain.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author: Liwh
 * @ClassName: Swagger2Config
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-06 12:21 PM
 */
@Configuration
@EnableSwagger2
@Profile({"!prod"})
@ConditionalOnMissingBean({Docket.class})
public class Swagger2Config {

    @Value("${swagger.api.version:1.0}")
    private String version;
    @Value("${swagger.api.title:}")
    private String title;

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .directModelSubstitute(Subject.class, Void.class)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liwh.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .build();
    }
}
