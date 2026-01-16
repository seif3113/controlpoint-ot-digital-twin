package com.controlpoint.ot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.controlpoint.ot.model.Asset;
import com.controlpoint.ot.model.AssetStatus;
import com.controlpoint.ot.repository.AssetRepository;

@SpringBootApplication
@EnableScheduling
public class OtDigitalTwinApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtDigitalTwinApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadSampleData(AssetRepository repository) {
        return args -> {

            Asset asset1 = Asset.builder()
                    .name("Hydraulic Pump A")
                    .type("Pump")
                    .status(AssetStatus.RUNNING)
                    .build();

            Asset asset2 = Asset.builder()
                    .name("Conveyor Belt 1")
                    .type("Conveyor")
                    .status(AssetStatus.STOPPED)
                    .build();

            repository.save(asset1);
            repository.save(asset2);

            System.out.println("Sample assets loaded successfully");
        };
    }
}
