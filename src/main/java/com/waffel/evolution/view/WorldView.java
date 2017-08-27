package com.waffel.evolution.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.waffel.evolution.configuration.ViewProperties;
import com.waffel.evolution.view.draw.DrawMaster;
import com.waffel.evolution.world.BoundedGrid;
import com.waffel.evolution.world.terrain.AbstractTerrain;
import com.waffel.evolution.world.terrain.AbstractTerrain;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.newdawn.slick.*;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * Created by Jonny on 8/23/2017.
 */
@Log4j2
@Singleton
public class WorldView {

    private final ViewProperties viewProperties;
    private final DrawMaster drawMaster;
    private final BoundedGrid<AbstractTerrain> grid;
    private final TerrainModelViewAdapter adapter;

    private final int boxSize;
    @Getter
    private Image masterImage;

    @Setter
    @Getter
    private float x = 0;

    @Setter
    @Getter
    private float y = 0;

    @Setter
    @Getter
    private float scale = 1;

    private boolean update = true;

    @Inject
    public WorldView(
            final ViewProperties viewProperties,
            final BoundedGrid<AbstractTerrain> grid,
            final DrawMaster drawMaster,
            final TerrainModelViewAdapter adapter
    ) throws SlickException {
        this.viewProperties = viewProperties;
        this.grid = grid;
        this.drawMaster = drawMaster;
        this.boxSize = Math.round(1 / this.viewProperties.getLineWidth());
        this.adapter = adapter;
    }

    private void createMasterImage() throws SlickException {
        this.masterImage = new Image(
                this.grid.getNumCols() * boxSize,
                this.grid.getNumRows() * boxSize
        );

        final Graphics graphics = masterImage.getGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.masterImage.getWidth(), this.masterImage.getHeight());

        Arrays.stream(this.grid.getOccupantArray())
                .flatMap(Arrays::stream)
                .map(location -> {
                    final int x = location.getCol() * boxSize;
                    final int y = location.getRow() * boxSize;
                    return this.adapter.mapToView(location.getContent(), x, y, boxSize, boxSize);
                })
                .forEach(view -> this.drawMaster.draw(graphics, view));
    }

    public void update(@Nonnull final GameContainer container) throws SlickException {
    }

    public void render(@Nonnull final GameContainer container, @Nonnull final Graphics g) throws SlickException {
        //Draw Background
        g.setColor(Color.white);
        g.fillRect(0, 0, container.getWidth(), container.getHeight());

        if (this.update) {
            this.createMasterImage();
            this.update = false;
        }

        g.drawImage(
                this.masterImage,
                0,
                0,
                container.getWidth(),
                container.getHeight(),
                this.x,
                this.y,
                this.masterImage.getWidth() * this.scale + this.x,
                this.masterImage.getHeight() * this.scale + this.y
        );
    }

}
