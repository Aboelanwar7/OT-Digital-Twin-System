package com.controlpoint.digitaltwin.scheduler;

import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.repository.AssetRepository;
import com.controlpoint.digitaltwin.service.SensorReadingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SensorSimulationJob {

    private final AssetRepository assetRepository;
    private final SensorReadingService readingService;

    public SensorSimulationJob(AssetRepository assetRepository, SensorReadingService readingService) {
        this.assetRepository = assetRepository;
        this.readingService = readingService;
    }

    @Scheduled(fixedRate = 5000)
    public void generateReadings() {
        List<Asset> assets = assetRepository.findAll();
        for (Asset asset : assets) {
            readingService.generateReadingFor(asset.getId());
        }
    }
}
