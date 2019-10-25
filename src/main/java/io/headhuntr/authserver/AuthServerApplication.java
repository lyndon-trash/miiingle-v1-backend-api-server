package io.headhuntr.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * good docs:
 * https://projects.spring.io/spring-security-oauth/docs/oauth2.html
 *
 * sample implementation:
 * https://www.baeldung.com/spring-security-oauth-jwt
 */
@SpringBootApplication
public class AuthServerApplication {

    /**
     * application health check:
     *
     * curl -X GET \
     *   http://localhost:8081/auth/actuator/health \
     *   -H 'Content-Type: application/json' \
     *   -H 'cache-control: no-cache'
     */
	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

}
