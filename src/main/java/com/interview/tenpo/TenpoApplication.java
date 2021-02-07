package com.interview.tenpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.interview")
public class TenpoApplication {
	public static void main(String[] args) {
		SpringApplication.run(TenpoApplication.class, args);
	}
}
