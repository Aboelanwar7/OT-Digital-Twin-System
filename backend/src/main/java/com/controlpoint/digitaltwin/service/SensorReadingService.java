package com.controlpoint.digitaltwin.service;

import com.controlpoint.digitaltwin.dto.SensorReadingResponse;

public interface SensorReadingService {
    SensorReadingResponse getLatestReading(Long assetId);
    void generateReadingFor(Long assetId);
}
