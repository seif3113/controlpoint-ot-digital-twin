package com.controlpoint.ot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controlpoint.ot.model.SensorReading;
import com.controlpoint.ot.service.SensorReadingService;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor

public class SensorReadingController {

    private final SensorReadingService sensorReadingService;

    @GetMapping("latest/{assetId}")
    public ResponseEntity<SensorReading> getSensorReadingByAssetId(
            @PathVariable
            @Min(value = 1, message = "Id must be at least 1") Long assetId) {
        return ResponseEntity.ok(sensorReadingService.getLatestSensorReading(assetId));
    }

}
