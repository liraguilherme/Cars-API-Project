package br.com.kodcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) //Para não ficar pedindo o login ao acessar o localhost
public class KodcarApplication {

	public static void main(String[] args) {
		SpringApplication.run(KodcarApplication.class, args);
	}

}
