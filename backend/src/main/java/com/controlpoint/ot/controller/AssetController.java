package com.controlpoint.ot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controlpoint.ot.dto.ApiErrorResponse;
import com.controlpoint.ot.dto.AssetCreateRequest;
import com.controlpoint.ot.model.Asset;
import com.controlpoint.ot.service.AssetService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @GetMapping("")
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssetById(
            @PathVariable
            @Min(value = 1, message = "Id must be at least 1") Long id
    ) {
        Asset asset = assetService.getAssetById(id);

        if (asset == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiErrorResponse(404, "Asset not found"));
        }

        return ResponseEntity.ok(asset);
    }

    @PostMapping("")
    public ResponseEntity<Asset> createAsset(@Valid @RequestBody AssetCreateRequest asset) {

        Asset validatedAsset = Asset.builder()
                .name(asset.getName())
                .type(asset.getType())
                .status(asset.getStatus())
                .build();

        Asset createdAsset = assetService.createAsset(validatedAsset);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAsset);
    }

}
