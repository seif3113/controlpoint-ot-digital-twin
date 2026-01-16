package com.controlpoint.ot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlpoint.ot.model.SensorReading;

import org.springframework.stereotype.Repository;

@Repository
public interface  SensorReadingRepository extends JpaRepository<SensorReading, Long> { 
    SensorReading findTopByAssetIdOrderByTimestampDesc(Long assetId);
}