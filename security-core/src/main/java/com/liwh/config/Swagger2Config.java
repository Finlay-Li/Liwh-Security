package com.liwh.config;

import com.liwh.domain.Subject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author: Liwh
 * @ClassName: Swagger2Config
 * @Description:
 * @version: 1.0.0
 * @date: 2018-12-06 12:21 PM
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = (input) -> {
            return input.isAnnotatedWith(ApiOperation.class) && input.findControllerAnnotation(RestController.class).isPresent() && input.findControllerAnnotation(Api.class).isPresent();
        };
        ApiSelectorBuilder var10000 = (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).directModelSubstitute(Subject.class, Void.class).globalOperationParameters(this.globalOperationParameters()).useDefaultResponseMessages(false).select();
        predicate.getClass();
        return var10000.apis(predicate::test).build();
    }

    private List<Parameter> globalOperationParameters() {
        List<Parameter> parameters = new ArrayList();
        parameters.add((new ParameterBuilder()).name("X-User-Id").description("登录用户Id(选填,用于模拟登录用户)").parameterType("header").required(false).modelRef(new ModelRef("long")).build());
        parameters.add((new ParameterBuilder()).name("X-User-Mobile").description("登录用户手机(选填,用于模拟登录用户)").parameterType("header").required(false).modelRef(new ModelRef("string")).build());
        return parameters;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("")
                .version("")
                .build();
    }
}
