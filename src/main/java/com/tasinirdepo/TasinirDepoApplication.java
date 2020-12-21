package com.tasinirdepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TasinirDepoApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(TasinirDepoApplication.class, args);
	}
}
