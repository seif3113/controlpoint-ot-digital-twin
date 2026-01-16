package com.controlpoint.ot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.controlpoint.ot.model.SensorReading;
import com.controlpoint.ot.repository.SensorReadingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SensorReadingService {
    
    private final SensorReadingRepository sensorReadingRepository;

    public SensorReading getLatestSensorReading(Long assetId) {
        return sensorReadingRepository.findTopByAssetIdOrderByTimestampDesc(assetId);
    }

    public void createSensorReadings(List<SensorReading> sensorReadings){
        sensorReadingRepository.saveAll(sensorReadings);
    }

}
