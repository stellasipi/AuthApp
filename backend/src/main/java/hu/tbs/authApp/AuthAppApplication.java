package hu.tbs.authApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
