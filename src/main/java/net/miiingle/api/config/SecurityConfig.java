package net.miiingle.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

/**
 * notes on how to properly configure JWT+OAuth2
 * https://github.com/spring-projects/spring-security/tree/master/samples/boot/oauth2resourceserver-static
 * https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2resourceserver
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
