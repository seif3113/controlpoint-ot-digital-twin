package com.controlpoint.ot.service;
import com.controlpoint.ot.model.Asset;
import com.controlpoint.ot.repository.AssetRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AssetService {

    private final AssetRepository assetRepository;

    public List<Asset> getAllAssets() {
        return assetRepository.findAll(); 
    }

    public Asset getAssetById(Long id){
        return assetRepository.findById(id).orElse(null);
    }

    public Asset createAsset(Asset asset){
        return assetRepository.save(asset);
    }

}