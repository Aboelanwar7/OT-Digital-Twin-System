package com.controlpoint.digitaltwin.dto;

import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.model.AssetStatus;

public record AssetResponse(
    Long id,
    String name,
    String type,
    AssetStatus status
) {
    public static AssetResponse from(Asset asset) {
        return new AssetResponse(asset.getId(), asset.getName(), asset.getType(), asset.getStatus());
    }
}
