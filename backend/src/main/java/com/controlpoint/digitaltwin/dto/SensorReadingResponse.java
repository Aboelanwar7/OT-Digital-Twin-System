package com.controlpoint.digitaltwin.dto;

import com.controlpoint.digitaltwin.model.SensorReading;
import java.time.LocalDateTime;

public record SensorReadingResponse(
    Long id,
    Long assetId,
    Double temperature,
    Double pressure,
    LocalDateTime timestamp
) {
    public static SensorReadingResponse from(SensorReading reading) {
        return new SensorReadingResponse(
            reading.getId(),
            reading.getAsset().getId(),
            reading.getTemperature(),
            reading.getPressure(),
            reading.getTimestamp()
        );
    }
}
