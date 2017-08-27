package com.waffel.evolution.world;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * A Container class that holds positional information for whatever it's holding.
 */
@EqualsAndHashCode
@Getter
public class Location<E> implements Comparable<Location> {

    private final int row; // row location in grid
    private final int col; // column location in grid
    private final E content;

    /**
     * New Location instance with no content
     *
     * @param row which row this is contained in
     * @param col which column this is contained in
     */
    public Location(final int row, final int col) {
        this(row, col, null);
    }

    /**
     * New Location instance
     *
     * @param row     which row this is contained in
     * @param col     which column this is contained in
     * @param content what content to hold that represents something in this location
     */
    public Location(int row, int col, E content) {
        this.row = row;
        this.col = col;
        this.content = content;
    }

    /**
     * Creates a copy of this location with content
     *
     * @param content to be added to the new location
     * @param <T>     the type of content to be added
     * @return a new location with the same position but new content
     */
    public <T> Location<T> withContent(T content) {
        return new Location<T>(this.row, this.col, content);
    }

    /**
     * Compares this location to <code>other</code> for ordering. Returns a
     * negative integer, zero, or a positive integer as this location is less
     * than, equal to, or greater than <code>other</code>. Locations are
     * ordered in row-major order. <br />
     * (Precondition: <code>other</code> is a <code>Location</code> object.)
     *
     * @param other the other location to test
     * @return a negative integer if this location is less than
     * <code>other</code>, zero if the two locations are equal, or a positive
     * integer if this location is greater than <code>other</code>
     */
    public int compareTo(@Nonnull final Location other) {
        int value = 0;
        if (this.getRow() < other.getRow()) {
            value = -1;
        }
        if (this.getRow() > other.getRow()) {
            value = 1;
        }
        if (this.getCol() < other.getCol()) {
            value = -1;
        }
        if (this.getCol() > other.getCol()) {
            value = 1;
        }
        return value;
    }

    /**
     * Creates a string that describes this location.
     *
     * @return a string with the row and column of this location, in the format
     * (row, col)
     */
    public String toString() {
        return String.format("(%d,%d)[%s]", this.row, this.col, this.content);
    }

    /**
     * @return if this location contains content
     */
    public boolean isFilled() {
        return Objects.nonNull(this.getContent());
    }

    /**
     * @return if this location contains no content
     */
    public boolean isEmpty() {
        return !isFilled();
    }

}
