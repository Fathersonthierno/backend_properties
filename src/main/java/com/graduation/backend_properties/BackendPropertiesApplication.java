package com.graduation.backend_properties;

import com.graduation.backend_properties.auth.AuthenticationService;
import com.graduation.backend_properties.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.graduation.backend_properties.modele.Role.*;

@SpringBootApplication
public class BackendPropertiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPropertiesApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.typeUtilisateur("Annonceur")
					.role(ANNONCEUR)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.typeUtilisateur("Acheteur")
					.role(ACHETEUR)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};

	}
}
