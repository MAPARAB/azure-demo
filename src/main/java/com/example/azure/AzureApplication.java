package com.example.azure;

import com.example.azure.model.Message;
import com.example.azure.pojo.ApiKeys;
import com.example.azure.util.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	@Bean
	public List<ApiKeys> initKeyStore()
	{
		List<ApiKeys> apiKeys = new ArrayList<>();
		apiKeys.add(new ApiKeys("343C-ED08-4137-827E", Stream.of(AppConstant.STUDENT_ROUTE_KEY, AppConstant.COURSE_ROUTE_KEY).collect(Collectors.toList())));
		apiKeys.add(new ApiKeys("FA48-EF0C-427E-8CCF", Stream.of(AppConstant.COURSE_ROUTE_KEY).collect(Collectors.toList())));
		return apiKeys;
	}

    @Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route(AppConstant.STUDENT_ROUTE_KEY, r -> r.path("/api/student-service/web/**")
                                .filters(f -> f.stripPrefix(2))
                                .uri("http://localhost:8085/web/greetings"))
                                .build();



    }

}
