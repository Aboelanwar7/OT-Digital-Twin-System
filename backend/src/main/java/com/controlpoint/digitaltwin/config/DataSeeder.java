package com.controlpoint.digitaltwin.config;

import com.controlpoint.digitaltwin.model.Asset;
import com.controlpoint.digitaltwin.model.AssetStatus;
import com.controlpoint.digitaltwin.repository.AssetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AssetRepository assetRepository;

    public DataSeeder(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public void run(String... args) {
        if (assetRepository.count() == 0) {
            assetRepository.saveAll(List.of(
                new Asset("Main Pump", "PUMP", AssetStatus.RUNNING),
                new Asset("Cooling Motor", "MOTOR", AssetStatus.STOPPED),
                new Asset("Assembly Conveyor", "CONVEYOR", AssetStatus.RUNNING)
            ));
        }
    }
}
