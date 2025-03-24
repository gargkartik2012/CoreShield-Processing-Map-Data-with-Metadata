package com.example.coreshieldassessment;

import com.example.coreshieldassessment.service.MapDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoreShieldAssessmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreShieldAssessmentApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MapDataService mapDataService) {
        return args -> mapDataService.processMapData();
    }

}
