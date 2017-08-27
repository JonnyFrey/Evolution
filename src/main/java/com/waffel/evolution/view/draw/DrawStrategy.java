package com.waffel.evolution.view.draw;

import org.newdawn.slick.Graphics;

/**
 * Represents a strategy on how to draw a {@link Drawable}
 */
@FunctionalInterface
public interface DrawStrategy {

    /**
     * Draws a drawable
     * @param g graphics to draw on
     * @param drawable the drawable to draw
     */
    void draw(Graphics g, Drawable drawable);

}
