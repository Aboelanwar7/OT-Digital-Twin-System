package com.controlpoint.digitaltwin.service;

import com.controlpoint.digitaltwin.dto.AssetRequest;
import com.controlpoint.digitaltwin.dto.AssetResponse;
import java.util.List;

public interface AssetService {
    AssetResponse createAsset(AssetRequest request);
    List<AssetResponse> getAllAssets();
    AssetResponse getAssetById(Long id);
}
