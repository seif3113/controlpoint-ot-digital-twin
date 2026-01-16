package com.controlpoint.ot.dto;

import com.controlpoint.ot.model.AssetStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AssetCreateRequest {

    @NotBlank(message = "Asset name is required")
    private String name;

    @NotBlank(message = "Asset type is required")
    private String type;

    @NotNull(message = "Asset status is required")
    private AssetStatus status;

}
