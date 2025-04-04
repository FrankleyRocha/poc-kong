package io.github.frankleyrocha.poc_kong_springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@SpringBootApplication
@RestController
@RequestMapping("/api")
public class PocKongSpringappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocKongSpringappApplication.class, args);
	}

	@GetMapping("/admin")
	public String adminEndpoint(HttpServletRequest request) {

		String username = request.getHeader("X-Consumer-Username");
		String groups = request.getHeader("X-Consumer-Groups");

		return "Admin works! User: %s, Groups: %s".formatted(username,groups);
	}

	@GetMapping("/authenticated")
	public String authenticatedEndpoint(HttpServletRequest request){

		String username = request.getHeader("X-Consumer-Username");
		String groups = request.getHeader("X-Consumer-Groups");

		return "Authenticated works! User: %s, Groups: %s".formatted(username, groups);
	}

	@GetMapping("/public")
	public String publicEndpoint() {
		return "Public works!";
	}

}