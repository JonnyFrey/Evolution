package com.waffel.evolution.view;

import com.waffel.evolution.world.terrain.AbstractTerrain;
import com.waffel.evolution.world.terrain.AbstractTerrain;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Small Pojo that holds information to help translate a {@link AbstractTerrain} model into a
 * {@link com.waffel.evolution.view.terrain.AbstractTerrainView}
 */
@Data
@AllArgsConstructor
public class TerrainInfo {

    private AbstractTerrain abstractTerrain;
    private int x;
    private int y;
    private int width;
    private int height;

}
