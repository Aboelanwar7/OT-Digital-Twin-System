package com.controlpoint.digitaltwin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    public Asset(String name, String type, AssetStatus status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }
}
