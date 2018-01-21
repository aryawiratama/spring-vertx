package com.belajar.springvertx;

import com.belajar.springvertx.config.ApplicationConfiguration;
import com.belajar.springvertx.verticle.BookCategoryVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringVertxApplication {

	@Autowired
	private BookCategoryVerticle bookCategoryVerticle;

	public static void main(String[] args) {
		SpringApplication.run(SpringVertxApplication.class, args);
	}

	@PostConstruct
	public void deployVerticles(){
		final Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(bookCategoryVerticle);
	}
}
