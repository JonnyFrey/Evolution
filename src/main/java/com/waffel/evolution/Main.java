package com.waffel.evolution;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.waffel.evolution.module.TerrainModule;
import com.waffel.evolution.module.ViewModule;
import com.waffel.evolution.view.NiftyBox;
import lombok.extern.log4j.Log4j2;
import org.newdawn.slick.SlickException;

/**
 * Main entry point of the program
 */
@Log4j2
public class Main {

    public static void main(final String... args) {
        LwjglUtils.setupProperties();
        final Injector injector = Guice.createInjector(new TerrainModule(), new ViewModule());
        try {
            final NiftyBox niftyBox = injector.getInstance(NiftyBox.class);
            niftyBox.start();
        } catch (final SlickException e) {
            LOGGER.error("Failed to run evolution", e);
        }
    }

}
