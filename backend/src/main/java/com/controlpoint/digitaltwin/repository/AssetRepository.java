package com.controlpoint.digitaltwin.repository;

import com.controlpoint.digitaltwin.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
