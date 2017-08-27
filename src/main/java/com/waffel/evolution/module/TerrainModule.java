package com.waffel.evolution.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.waffel.evolution.configuration.TerrainProperties;
import com.waffel.evolution.world.BoundedGrid;
import com.waffel.evolution.world.terrain.AbstractTerrain;
import com.waffel.evolution.world.terrain.Forrest;
import com.waffel.evolution.world.terrain.Water;

import java.util.stream.IntStream;

/**
 * Module responsible for building the world map
 */
public class TerrainModule extends AbstractModule {

    @Override
    protected void configure() {

    }

    @Provides
    public BoundedGrid<AbstractTerrain> provideTerrainGrid(final TerrainProperties terrainProperties) {
        final BoundedGrid<AbstractTerrain> grid = new BoundedGrid<>(terrainProperties.getRows(), terrainProperties.getColumns());
        int totalSize = grid.getNumRows() * grid.getNumCols();
        IntStream.range(0, totalSize)
                .mapToObj(operand -> Math.random() > .75 ? new Water() : new Forrest())
                .forEach(terrain -> grid.getRandomEmptyLocation()
                        .ifPresent(
                                location -> grid.put(location.withContent(terrain))
                        )
                );
        return grid;
    }

}
