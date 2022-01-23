package com.example;

import brave.SpanCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@SpringBootApplication
public class Service1 implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Service1.class, args);
	}
	@Autowired
	private SpanCustomizer spanCustomizer;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(
				new HandlerInterceptor() {
					@Override
					public boolean preHandle(
							HttpServletRequest request, HttpServletResponse response, Object handler)
							throws Exception {
						spanCustomizer.tag("uuid", UUID.randomUUID().toString());
						return true;
					}
				});
	}
}
