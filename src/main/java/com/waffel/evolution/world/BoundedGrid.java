package com.waffel.evolution.world;

import com.google.common.collect.Range;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class BoundedGrid<E> {

    @Getter
    private Location<E>[][] occupantArray; // the array storing the grid elements
    private static final Random RANDOM = new Random();

    public BoundedGrid(final int rows, final int cols) {
        checkArgument(rows > 0, "Rows must be positive (found:%s)", rows);
        checkArgument(cols > 0, "Rows must be positive (found:%s)", cols);
        //noinspection unchecked
        occupantArray = new Location[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                this.occupantArray[r][c] = new Location<E>(r, c);
            }
        }
    }

    public int getNumRows() {
        return occupantArray.length;
    }

    public int getNumCols() {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }

    public boolean isValid(final int row, final int col) {
        return Range.closedOpen(0, this.getNumRows()).contains(row) &&
                Range.closedOpen(0, this.getNumCols()).contains(col);
    }

    @Nonnull
    public List<Location<E>> getOccupiedLocations() {
        return Arrays.stream(this.occupantArray)
                .flatMap(Arrays::stream)
                .filter(Location::isFilled)
                .collect(Collectors.toList());
    }

    @Nonnull
    public Location<E> get(final int row, final int col) {
        checkArgument(this.isValid(row, col), "Location %s is not a valid location");
        return this.occupantArray[row][col];
    }

    @Nonnull
    public Location<E> put(@Nonnull final Location<E> newLoc) {
        checkNotNull(newLoc);
        checkArgument(this.isValid(newLoc.getRow(), newLoc.getCol()), "Location %s is not a valid location");
        // Add the object to the grid.
        final Location<E> oldLoc = this.get(newLoc.getRow(), newLoc.getCol());
        this.occupantArray[newLoc.getRow()][newLoc.getCol()] = newLoc;
        return oldLoc;
    }

    public Optional<E> remove(final int row, final int col) {
        return Optional.of(this.put(new Location<E>(row, col, null)).getContent());
    }

    @Nonnull
    public Optional<Location<E>> getAdjacentLocation(final int row, final int col, @Nonnull final Direction direction) {
        checkNotNull(direction);
        final Location<E> location = this.get(row, col);
        final Location newTranslatedLocation = direction.applyTranslation(location);
        if (this.isValid(newTranslatedLocation.getRow(), newTranslatedLocation.getCol())) {
            return Optional.of(this.get(newTranslatedLocation.getRow(), newTranslatedLocation.getCol()));
        }
        return Optional.empty();
    }

    @NonNull
    public Optional<Location<E>> getRandomEmptyLocation() {
        List<Location<E>> emptyLocations = Arrays.stream(this.occupantArray)
                .flatMap(Arrays::stream)
                .filter(Location::isEmpty)
                .collect(Collectors.toList());
        return emptyLocations.isEmpty() ?
                Optional.empty() : Optional.of(emptyLocations.get(RANDOM.nextInt(emptyLocations.size())));
    }

}
