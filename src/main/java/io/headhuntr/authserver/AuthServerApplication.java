package io.headhuntr.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * good docs:
 * https://projects.spring.io/spring-security-oauth/docs/oauth2.html
 *
 * sample implementation:
 * https://www.baeldung.com/spring-security-oauth-jwt
 */
@SpringBootApplication
@EnableResourceServer
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

}
