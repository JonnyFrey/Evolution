package com.waffel.evolution.world;

import java.util.function.Function;

/**
 * Represents a cardinal direction to translate a location
 */
public enum Direction {

    NORTH(location -> new Location<>(location.getRow() - 1, location.getCol())),
    SOUTH(location -> new Location<>(location.getRow() + 1, location.getCol())),
    WEST(location -> new Location<>(location.getRow(), location.getCol() - 1)),
    EAST(location -> new Location<>(location.getRow(), location.getCol() + 1));

    public Function<Location, Location> translateFunction;

    Direction(final Function<Location, Location> translateFunction) {
        this.translateFunction = translateFunction;
    }

    /**
     * Returns a new location based on a direction. Note this location is in a grid this will not have the
     * contents that grid will contain.
     *
     * @param location location you want to translate
     * @return a new location with no content
     */
    public Location applyTranslation(final Location location) {
        return this.translateFunction.apply(location);
    }
}
