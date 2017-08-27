package com.waffel.evolution.view.draw;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Class that knows how to take a {@link Drawable} and apply the appropriate {@link DrawStrategy} to it. There are two
 * ways to register a {@link DrawStrategy}; Through the instance or through the class. If there is no instance mapping
 * then it will search for a class mapping. If there is no class mapping then it will default to the nullStrategy.
 */
@SuppressWarnings("unused")
public class DrawMaster {

    private final Map<Drawable, DrawStrategy> instanceMap;
    private final Map<Class<? extends Drawable>, DrawStrategy> classMap;
    private final DrawStrategy nullStrategy;

    /**
     * New {@link DrawMaster} instance
     */
    public DrawMaster() {
        this.instanceMap = new WeakHashMap<>();
        this.classMap = new HashMap<>();
        this.nullStrategy = BasicDrawStrategy.fillColor(Color.white);
    }

    /**
     * Draws All drawables onto the graphics through the Strategy Mapping. Not it will attempt to draw all drawables
     * concurrently in a multi-threaded environment.
     *
     * @param g         graphics to draw on
     * @param drawables drawable to draw.
     */
    public void draw(final Graphics g, final List<? extends Drawable> drawables) {
        drawables.parallelStream().forEach(o -> draw(g, o));
    }

    /**
     * Draws the drawable onto the graphics through the Strategy Mapping.
     *
     * @param g        graphics to draw on
     * @param drawable drawable to draw.
     */
    public void draw(final Graphics g, final Drawable drawable) {
        DrawStrategy strategy = this.instanceMap.get(drawable);
        if (strategy == null) {
            strategy = this.classMap.getOrDefault(drawable.getClass(), this.nullStrategy);
        }
        strategy.draw(g, drawable);
    }

    /**
     * Adds a DrawStrategy to an Instance
     *
     * @param drawable the drawable that you want to draw
     * @param strategy descibes how to draw the drawable
     * @return itself
     */
    public DrawMaster addStrategy(final Drawable drawable, final DrawStrategy strategy) {
        this.instanceMap.put(drawable, strategy);
        return this;
    }

    /**
     * Adds a {@link DrawStrategy} to an Instance
     *
     * @param drawClass the drawable that you want to draw
     * @param strategy  describes how to draw the drawable
     * @return itself
     */
    public DrawMaster addStrategy(final Class<? extends Drawable> drawClass, final DrawStrategy strategy) {
        this.classMap.put(drawClass, strategy);
        return this;
    }

    /**
     * Removes a Drawable's {@link DrawStrategy}
     *
     * @param drawable the drawable to remove
     * @return itself
     */
    public DrawMaster removeStrategy(final Drawable drawable) {
        this.instanceMap.remove(drawable);
        return this;
    }

    /**
     * Removes a Drawable Class's {@link DrawStrategy}
     *
     * @param drawClass the class to remove
     * @return itself
     */
    public DrawMaster removeStrategy(final Class<? extends Drawable> drawClass) {
        this.classMap.remove(drawClass);
        return this;
    }

}