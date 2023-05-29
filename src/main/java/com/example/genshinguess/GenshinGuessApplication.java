package com.example.genshinguess;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.genshinguess.mapper")
public class GenshinGuessApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenshinGuessApplication.class, args);
	}

}
