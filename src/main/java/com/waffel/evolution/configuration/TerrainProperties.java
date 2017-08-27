package com.waffel.evolution.configuration;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Data;

/**
 * Holds Constants for the Terrain Generation
 */
@Data
@Singleton
public class TerrainProperties {

    private int rows = 100;
    private int columns = 100;

    @Inject
    public TerrainProperties() {
    }
}
