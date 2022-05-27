package main;

import main.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DekanatApplication {
    public static void main(String[] args) {
        SpringApplication.run(DekanatApplication.class, args);
    }

    @Bean
    public User createUser() {
        return new User();
    }
}
