package com.web3.with;

import com.web3.with.security.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class WithApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithApplication.class, args);
	}
}