package com.waffel.evolution.world;

import java.util.function.Function;

public enum Direction {

    NORTH(location -> new Location<>(location.getRow() - 1, location.getCol())),
    SOUTH(location -> new Location<>(location.getRow() + 1, location.getCol())),
    WEST(location -> new Location<>(location.getRow(), location.getCol() - 1)),
    EAST(location -> new Location<>(location.getRow(), location.getCol() + 1));

    public Function<Location, Location> translateFunction;

    Direction(final Function<Location, Location> translateFunction) {
        this.translateFunction = translateFunction;
    }

    public Location applyTranslation(final Location location) {
        return this.translateFunction.apply(location);
    }
}
