package org.gas_utility.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Creates Instance of GroupedOpenApi implementing Swagger UI documentation.
     * Swagger UI provides request call support for apis.
     * @return Instance of GroupedOpenApi.
     */
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("org.gasutility.controller")
                .pathsToMatch("/**")
                .build();
    }
}
