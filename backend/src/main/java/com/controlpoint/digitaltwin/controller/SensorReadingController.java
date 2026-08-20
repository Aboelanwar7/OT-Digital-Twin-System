package com.controlpoint.digitaltwin.controller;

import com.controlpoint.digitaltwin.dto.SensorReadingResponse;
import com.controlpoint.digitaltwin.service.SensorReadingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assets/{id}/readings")
public class SensorReadingController {

    private final SensorReadingService readingService;

    public SensorReadingController(SensorReadingService readingService) {
        this.readingService = readingService;
    }

    @GetMapping("/latest")
    public ResponseEntity<SensorReadingResponse> getLatestReading(@PathVariable Long id) {
        SensorReadingResponse response = readingService.getLatestReading(id);
        if (response == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
