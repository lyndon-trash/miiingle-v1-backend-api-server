package io.headhuntr.authserver.shared;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig {

    //TODO: dont hardcode, use config please
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                log.info("Configuring CORS");

                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200", "https://dev.miiingle.net");
            }
        };
    }
}
