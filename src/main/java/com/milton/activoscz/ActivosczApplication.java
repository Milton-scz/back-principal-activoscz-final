package com.milton.activoscz;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.milton.activoscz.Exception.GraphQLErrorAdapter;
import com.milton.activoscz.Graphql.Repository.UserRepository;
import com.milton.activoscz.Model.User;
import com.milton.activoscz.Repository.ActivoRepository;
import com.milton.activoscz.Repository.CategoriaRepository;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

@SpringBootApplication(scanBasePackages = "com.milton") @ComponentScan(basePackageClasses = ActivosczApplication.class) 
public class ActivosczApplication {
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(ActivosczApplication.class, args);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                // Separate client and server errors
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> processedErrors = new ArrayList<>();
                processedErrors.addAll(clientErrors);
                processedErrors.addAll(serverErrors);
                return processedErrors;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public CommandLineRunner initData(ActivoRepository activoRepository, CategoriaRepository categoriaRepository) {
        return args -> {
       
            if (!userRepository.existsByEmail("milton@gmail.com")) {
                User user = new User("Milton", "milton@gmail.com","$2y$12$WKTgxqj7didOTJkkBkKeTu3bun4vPaojTkRcYXY/CWF9HK2CUvfb2" ,
                		User.Role.ADMIN);
                userRepository.save(user);
        }
       
    };
    }
 
  
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200") 
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
