package com.waffel.evolution.view.draw;

import org.newdawn.slick.Color;

/**
 * Created by Jonny on 8/27/17.
 */
public class BasicDrawStrategy {

    public static DrawStrategy fillColor(final Color color) {
        return (g, drawable) -> {
            final Color originalColor = g.getColor();
            g.setColor(color);
            g.fillRect(drawable.getX(), drawable.getY(), drawable.getWidth(), drawable.getHeight());
            g.setColor(originalColor);
        };
    }

}
