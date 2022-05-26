package com.example.azure;

import com.example.azure.model.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="com.example.azure")
public class AzureApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(AzureApplication.class, args);
	}

	@Bean
	public Message getMessage()
	{
		return new Message();
	}
}
