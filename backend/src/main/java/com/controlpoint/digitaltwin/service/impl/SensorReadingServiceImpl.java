package com.controlpoint.digitaltwin.service.impl;

import com.controlpoint.digitaltwin.dto.SensorReadingResponse;
import com.controlpoint.digitaltwin.exception.AssetNotFoundException;
import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.model.AssetStatus;
import com.controlpoint.digitaltwin.model.SensorReading;
import com.controlpoint.digitaltwin.repository.AssetRepository;
import com.controlpoint.digitaltwin.repository.SensorReadingRepository;
import com.controlpoint.digitaltwin.service.SensorReadingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    private final SensorReadingRepository readingRepository;
    private final AssetRepository assetRepository;
    private final Random random = new Random();

    public SensorReadingServiceImpl(SensorReadingRepository readingRepository, AssetRepository assetRepository) {
        this.readingRepository = readingRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public SensorReadingResponse getLatestReading(Long assetId) {
        if (!assetRepository.existsById(assetId)) {
            throw new AssetNotFoundException(assetId);
        }
        return readingRepository.findTopByAsset_IdOrderByTimestampDesc(assetId)
                .map(SensorReadingResponse::from)
                .orElse(null);
    }

    @Override
    @Transactional
    public void generateReadingFor(Long assetId) {
        Asset asset = assetRepository.findById(assetId)
            .orElseThrow(() -> new AssetNotFoundException(assetId));

        SensorReading reading = new SensorReading();
        reading.setAsset(asset);
        reading.setTemperature(20.0 + random.nextDouble() * 100.0); // 20 - 120
        reading.setPressure(1.0 + random.nextDouble() * 9.0); // 1 - 10
        reading.setTimestamp(LocalDateTime.now());
        readingRepository.save(reading);

        int statusRoll = random.nextInt(100);
        if (statusRoll < 80) {
            asset.setStatus(AssetStatus.RUNNING);
        } else if (statusRoll < 90) {
            asset.setStatus(AssetStatus.STOPPED);
        } else {
            asset.setStatus(AssetStatus.ALARM);
        }
        assetRepository.save(asset);
    }
}
