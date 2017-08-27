package com.waffel.evolution.configuration;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Data;

/**
 * Holds constants for the nifty library
 */
@Data
@Singleton
public class NiftyProperties {

    private int width = 720;
    private int height = 720;
    private boolean showFps = false;
    private boolean fullscreen = false;
    private String title = "Evolution";
    private String niftyXml = "nifty.xml";
    private String startScreen = "start";

    @Inject
    public NiftyProperties() {
    }
}
