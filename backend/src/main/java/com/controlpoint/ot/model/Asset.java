package com.controlpoint.ot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder  
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assets")
public class Asset {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String type;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetStatus status;    
}