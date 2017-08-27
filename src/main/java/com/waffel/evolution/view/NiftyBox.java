package com.waffel.evolution.view;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.waffel.evolution.configuration.NiftyProperties;
import com.waffel.evolution.controller.WorldViewMouseListener;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.slick2d.NiftyOverlayGame;
import lombok.extern.log4j.Log4j2;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The Main Game which handles delegating the graphics and input
 */
@Log4j2
public class NiftyBox extends NiftyOverlayGame {

    private final AppGameContainer container;
    private final NiftyProperties niftyProperties;
    private final Injector injector;
    private WorldView worldView;

    @Inject
    public NiftyBox(
            @Nonnull final NiftyProperties niftyProperties,
            @Nonnull final Injector injector
    ) throws SlickException {
        super();
        this.niftyProperties = checkNotNull(niftyProperties);
        this.container = new AppGameContainer(this);
        this.container.setShowFPS(this.niftyProperties.isShowFps());
        this.container.setDisplayMode(
                this.niftyProperties.getWidth(),
                this.niftyProperties.getHeight(),
                this.niftyProperties.isFullscreen());
        this.container.setVSync(true);
        this.injector = injector;
    }

    @Override
    protected void initGameAndGUI(@Nonnull final GameContainer container) {
        this.initNifty(container);
        this.worldView = this.injector.getInstance(WorldView.class);
        container.getInput().addMouseListener(this.injector.getInstance(WorldViewMouseListener.class));
    }

    @Override
    protected void renderGame(@Nonnull final GameContainer container, @Nonnull final Graphics g) {
        g.clear();
        try {
            this.worldView.render(container, g);
        } catch (SlickException e) {
            LOGGER.info("Failed to draw World View ", e);
        }
    }

    @Override
    protected void updateGame(@Nonnull final GameContainer container, final int delta) {
    }

    @Override
    protected void prepareNifty(@Nonnull final Nifty nifty) {
        nifty.fromXml(this.niftyProperties.getNiftyXml(), this.niftyProperties.getStartScreen());
    }

    @Override
    public boolean closeRequested() {
        return true;
    }

    /**
     * Start running the game
     *
     * @throws SlickException Indicates a failure to initialise the system
     */
    public void start() throws SlickException {
        this.container.start();
    }

    @Override
    public String getTitle() {
        return this.niftyProperties.getTitle();
    }
}
