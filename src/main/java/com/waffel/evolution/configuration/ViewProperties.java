package com.waffel.evolution.configuration;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Data;

/**
 * Holds Constants for the View
 */
@Data
@Singleton
public class ViewProperties {

    private float lineWidth = 0.05f;
    private float scrollSpeed = 0.00001f;

    @Inject
    public ViewProperties() {
    }
}
