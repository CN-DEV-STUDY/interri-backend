package com.cn.interri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InterriBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterriBackendApplication.class, args);
	}

}
