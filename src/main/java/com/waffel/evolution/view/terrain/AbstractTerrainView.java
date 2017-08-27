package com.waffel.evolution.view.terrain;

import com.waffel.evolution.view.TerrainInfo;
import com.waffel.evolution.view.draw.Drawable;
import lombok.AllArgsConstructor;

/**
 * Describes how to draw a bit of Terrain
 */
@AllArgsConstructor
public abstract class AbstractTerrainView implements Drawable {

    private final TerrainInfo info;

    @Override
    public int getX() {
        return this.info.getX();
    }

    @Override
    public int getY() {
        return this.info.getY();
    }

    @Override
    public int getWidth() {
        return this.info.getWidth();
    }

    @Override
    public int getHeight() {
        return this.info.getHeight();
    }
}
