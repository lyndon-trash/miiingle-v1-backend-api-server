package net.miiingle.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Miiingle.NET API",
				description = "API for the Mobile Application",
				version = "0.0.1",
				contact = @Contact(
						email = "contact@miiingle.net"
				)
		),
		servers = {
				@Server(
					url = "http://localhost:8080",
					description = "Local"
				),
				@Server(
						url = "https://api.miiingle.net",
						description = "Prod"
				)
		}
		)
@SpringBootApplication
public class ApiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiServerApplication.class, args);
	}

}
