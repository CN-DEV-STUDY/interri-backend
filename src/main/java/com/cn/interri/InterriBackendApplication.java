package com.cn.interri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching // Redis Cache 사용하기 위한 어노테이션
@EnableJpaAuditing
@SpringBootApplication
public class InterriBackendApplication {

	static {
		// 이 설정을 하지 않은 경우 서비스가 실행되는 시점에 약간의 지연이 발생하고 예외가 발생한다.
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}
	public static void main(String[] args) {
		SpringApplication.run(InterriBackendApplication.class, args);
	}

}
