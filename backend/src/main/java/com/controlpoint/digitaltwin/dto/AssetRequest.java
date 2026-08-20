package com.controlpoint.digitaltwin.dto;

import jakarta.validation.constraints.NotBlank;
import com.controlpoint.digitaltwin.model.AssetStatus;

public record AssetRequest(
    @NotBlank(message = "Name cannot be blank")
    String name,
    @NotBlank(message = "Type cannot be blank")
    String type,
    AssetStatus status
) {}
