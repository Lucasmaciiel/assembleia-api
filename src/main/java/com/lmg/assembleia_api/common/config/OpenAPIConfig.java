package com.lmg.assembleia_api.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {

        var descricao = """
                No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação
                """;

        final Info info = new Info()
                .title("Assembleia API")
                .description(descricao);
        return new OpenAPI().info(info);
    }
}
