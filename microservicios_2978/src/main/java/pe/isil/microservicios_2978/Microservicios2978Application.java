package pe.isil.microservicios_2978;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Microservicios2978Application {

	public static void main(String[] args) {
		SpringApplication.run(Microservicios2978Application.class, args);
	}

	// Beans
	@Bean
	public PasswordEncoder encoder() { // For store strong passwords
		return new BCryptPasswordEncoder();
	}

}
