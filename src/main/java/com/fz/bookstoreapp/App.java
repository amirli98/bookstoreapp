package com.fz.bookstoreapp;

import com.fz.bookstoreapp.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackageClasses = {App.class, SwaggerConfig.class})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
