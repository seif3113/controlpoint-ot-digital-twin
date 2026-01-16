package com.controlpoint.ot.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.controlpoint.ot.model.Asset;
import com.controlpoint.ot.model.AssetStatus;
import com.controlpoint.ot.model.SensorReading;
import com.controlpoint.ot.service.AssetService;
import com.controlpoint.ot.service.SensorReadingService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class SensorSimulator {

    private final AssetService assetService;
    private final SensorReadingService sensorReadingService;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    @Scheduled(fixedRate = 5000)
    public void executeTask() {

        List<Asset> assets = assetService.getAllAssets();
        List<SensorReading> newSensorReadings = new ArrayList<>();

        if (assets.isEmpty()) {
            return;
        }

        assets.forEach(asset -> {
            Double temperature = getRandomTemperature();
            Double pressure = getRandomPressure();
            AssetStatus newStatus = getRandomStatus();

            asset.setStatus(newStatus);

            SensorReading sensorReading = SensorReading.builder()
                    .assetId(asset.getId())
                    .temperature(temperature)
                    .pressure(pressure)
                    .build();

            newSensorReadings.add(sensorReading);

        });

        sensorReadingService.createSensorReadings(newSensorReadings);
    }

    public AssetStatus getRandomStatus() {
        AssetStatus[] values = AssetStatus.values();
        return values[random.nextInt(values.length)];
    }

    public double getRandomTemperature() {
        double minTemp = -40.0, maxTemp = 125.0;
        double temperature = minTemp + (random.nextDouble() * (maxTemp - minTemp));
        temperature = Math.round(temperature * 10.0) / 10.0;
        return temperature;
    }

    public double getRandomPressure() {
        double pressure = random.nextDouble() * 10;
        pressure = Math.round(pressure * 10.0) / 10.0;
        return pressure;
    }
}
