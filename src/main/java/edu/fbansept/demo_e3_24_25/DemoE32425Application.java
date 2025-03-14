package edu.fbansept.demo_e3_24_25;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class DemoE32425Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoE32425Application.class, args);
    }

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }

}
