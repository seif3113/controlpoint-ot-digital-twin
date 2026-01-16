package com.controlpoint.ot.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sensor_readings")

public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double temperature;

    @Column(nullable = false)
    private Double pressure;

    @CreationTimestamp
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private Long assetId;
}
