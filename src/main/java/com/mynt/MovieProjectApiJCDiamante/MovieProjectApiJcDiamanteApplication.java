package com.mynt.MovieProjectApiJCDiamante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

@EnableGlobalAuthentication
@SpringBootApplication
public class MovieProjectApiJcDiamanteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieProjectApiJcDiamanteApplication.class, args);
	}

}
