package com.sda.cats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatsApplication.class,
				args);
	}

}
