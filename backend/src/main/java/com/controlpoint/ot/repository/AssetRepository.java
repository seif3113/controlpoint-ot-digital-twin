package com.controlpoint.ot.repository;

import com.controlpoint.ot.model.Asset;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByType(String type);    
}
