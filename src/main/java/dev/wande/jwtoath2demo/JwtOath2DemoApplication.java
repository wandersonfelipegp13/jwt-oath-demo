package dev.wande.jwtoath2demo;

import dev.wande.jwtoath2demo.config.RsakeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsakeyProperties.class)
public class JwtOath2DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtOath2DemoApplication.class, args);
	}

}
