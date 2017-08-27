package com.waffel.evolution.controller;

import com.google.inject.Inject;
import com.waffel.evolution.configuration.ViewProperties;
import com.waffel.evolution.view.WorldView;
import lombok.extern.log4j.Log4j2;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

/**
 * A Listener that changes the view camera on world
 */
@Log4j2
public class WorldViewMouseListener implements MouseListener {

    private final WorldView worldView;
    private final ViewProperties viewProperties;

    @Inject
    public WorldViewMouseListener(final WorldView worldView, final ViewProperties viewProperties) {
        this.worldView = worldView;
        this.viewProperties = viewProperties;
    }

    @Override
    public void mouseWheelMoved(final int change) {
        final float updatedScale = this.worldView.getScale() + (change * this.viewProperties.getScrollSpeed());

        //Snaps the scale such that it fits within the range [0.1, 1]
        final float newScale = Math.max(
                Math.min(updatedScale, 1),
                0.1f
        );

        this.worldView.setScale(newScale);
        this.fixView(this.worldView.getX(), this.worldView.getY());
    }

    @Override
    public void mouseDragged(final int oldx, final int oldy, final int newx, final int newy) {
        final float scale = this.worldView.getScale();
        final int deltaX = (int) Math.ceil((oldx - newx) * scale);
        final int deltaY = (int) Math.ceil((oldy - newy) * scale);
        this.fixView(deltaX + this.worldView.getX(), deltaY + this.worldView.getY());
    }

    /**
     * Sets the new x and y values for the camera to view. Will snap the x and y value to fit within the bound
     * of the world
     *
     * @param newX new x coordinate to set the camera
     * @param newY new y coordinate to set the camera
     */
    private void fixView(final float newX, final float newY) {
        final float x = this.worldView.getX();
        final float y = this.worldView.getY();
        final float scale = this.worldView.getScale();
        final int width = this.worldView.getMasterImage().getWidth();
        final int height = this.worldView.getMasterImage().getHeight();

        float updatedX = Math.max(0, newX);
        float updatedY = Math.max(0, newY);


        if (width * scale + x > width) {
            updatedX = width - (width * scale);
        }
        if (height * scale + y > height) {
            updatedY = height - (height * scale);
        }

        this.worldView.setX(updatedX);
        this.worldView.setY(updatedY);

    }

    @Override
    public void mouseClicked(final int button, final int x, final int y, final int clickCount) {

    }

    @Override
    public void mousePressed(final int button, final int x, final int y) {

    }

    @Override
    public void mouseReleased(final int button, final int x, final int y) {

    }

    @Override
    public void mouseMoved(final int oldx, final int oldy, final int newx, final int newy) {

    }

    @Override
    public void setInput(final Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }
}
