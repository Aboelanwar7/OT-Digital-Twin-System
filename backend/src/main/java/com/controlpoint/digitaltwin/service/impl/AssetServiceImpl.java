package com.controlpoint.digitaltwin.service.impl;

import com.controlpoint.digitaltwin.dto.AssetRequest;
import com.controlpoint.digitaltwin.dto.AssetResponse;
import com.controlpoint.digitaltwin.exception.AssetNotFoundException;
import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.model.AssetStatus;
import com.controlpoint.digitaltwin.repository.AssetRepository;
import com.controlpoint.digitaltwin.service.AssetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    @Transactional
    public AssetResponse createAsset(AssetRequest request) {
        Asset asset = new Asset(
            request.name(), 
            request.type(), 
            request.status() != null ? request.status() : AssetStatus.STOPPED
        );
        Asset saved = assetRepository.save(asset);
        return AssetResponse.from(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssetResponse> getAllAssets() {
        return assetRepository.findAll().stream()
                .map(AssetResponse::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AssetResponse getAssetById(Long id) {
        return assetRepository.findById(id)
                .map(AssetResponse::from)
                .orElseThrow(() -> new AssetNotFoundException(id));
    }
}
