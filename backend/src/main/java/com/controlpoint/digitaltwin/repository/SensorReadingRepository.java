package com.controlpoint.digitaltwin.repository;

import com.controlpoint.digitaltwin.model.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    Optional<SensorReading> findTopByAsset_IdOrderByTimestampDesc(Long assetId);
}
