package com.dme.DormitoryProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class DormitoryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DormitoryProjectApplication.class, args);
	}

}
