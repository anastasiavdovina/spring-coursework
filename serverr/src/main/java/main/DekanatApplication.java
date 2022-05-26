package main;

import main.entity.*;
import main.repository.GroupsRepository;
import main.repository.MarksRepository;
import main.repository.PeopleRepository;
import main.repository.SubjectsRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;

@SpringBootApplication
public class DekanatApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(DekanatApplication.class, args);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
