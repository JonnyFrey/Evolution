package com.waffel.evolution;

import java.nio.file.Paths;

/**
 * Simple Utility class to help setup lwjgl
 */
public class LwjglUtils {

    private LwjglUtils() {
    }

    /**
     * Sets the appropriate System Properties to set up lwjgl. Must be called before the using lwjgl.
     */
    public static void setupProperties() {
        final String libraryNatives = Paths.get("natives/").toFile().getAbsolutePath();
        System.setProperty("org.lwjgl.librarypath", libraryNatives);
        System.setProperty("net.java.games.input.librarypath", libraryNatives);
    }

}
