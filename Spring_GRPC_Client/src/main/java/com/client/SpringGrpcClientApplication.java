package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.client")
public class SpringGrpcClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGrpcClientApplication.class, args);
	}

}
