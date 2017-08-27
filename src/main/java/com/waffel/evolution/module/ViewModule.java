package com.waffel.evolution.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.waffel.evolution.view.draw.BasicDrawStrategy;
import com.waffel.evolution.view.draw.DrawMaster;
import com.waffel.evolution.view.terrain.EmptyView;
import com.waffel.evolution.view.terrain.ForrestView;
import com.waffel.evolution.view.terrain.WaterView;
import org.newdawn.slick.Color;

/**
 * Module responsible for building the view layer
 */
public class ViewModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    public DrawMaster providesDrawMaster() {
        return new DrawMaster()
                .addStrategy(EmptyView.class, BasicDrawStrategy.fillColor(Color.white))
                .addStrategy(ForrestView.class, BasicDrawStrategy.fillColor(new Color(0, 0xCC, 0)))
                .addStrategy(WaterView.class, BasicDrawStrategy.fillColor(new Color(42, 0xEE, 0xF4)));
    }

}
