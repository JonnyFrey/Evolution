package com.waffel.evolution.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.waffel.evolution.view.terrain.AbstractTerrainView;
import com.waffel.evolution.view.terrain.EmptyView;
import com.waffel.evolution.view.terrain.ForrestView;
import com.waffel.evolution.view.terrain.WaterView;
import com.waffel.evolution.world.terrain.AbstractTerrain;
import com.waffel.evolution.world.terrain.Forrest;
import com.waffel.evolution.world.terrain.AbstractTerrain;
import com.waffel.evolution.world.terrain.Water;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.*;

/**
 * An adapter that knows how to translate {@link AbstractTerrain} model into a {@link AbstractTerrainView}
 */
@Singleton
public class TerrainModelViewAdapter {

    private final Map<Class<? extends AbstractTerrain>, Function<TerrainInfo, AbstractTerrainView>> modelMapper;

    @Inject
    public TerrainModelViewAdapter() {
        this.modelMapper = new HashMap<>();
        this.modelMapper.put(Forrest.class, ForrestView::new);
        this.modelMapper.put(Water.class, WaterView::new);
    }

    /**
     * Converts the terrain into a terrain view
     * @param abstractTerrain the terrain to translate
     * @param x the left corner x position to draw
     * @param y the right corner y position to draw
     * @param width the width of the image to draw
     * @param height the height of the image to draw
     * @param <T> any that is an AbstractTerrain
     * @return
     */
    public <T extends AbstractTerrain> AbstractTerrainView mapToView(
            final AbstractTerrain abstractTerrain,
            final int x,
            final int y,
            final int width,
            final int height
    ) {
        final TerrainInfo info = new TerrainInfo(abstractTerrain, x, y, width, height);
        if (isNull(abstractTerrain)) {
            return new EmptyView(info);
        }
        return this.modelMapper.getOrDefault(abstractTerrain.getClass(), info1 -> null).apply(info);
    }

}
