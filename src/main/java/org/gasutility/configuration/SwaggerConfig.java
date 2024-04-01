package org.gasutility.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("org.gasutility.controller")
                .pathsToMatch("/**")
                .build();
    }
}
