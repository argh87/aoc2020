package de.argh.aoc.day24;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Tile {

    static final Set<Tile> STEPPED_ON = new HashSet<>();
    static final Tile START = new Tile(0, 0);

    static {
        STEPPED_ON.add(START);
    }

    private final int x;
    private final int y;
    private boolean black = false;

    Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    void go(List<Direction> walkway) {
        final Iterator<Direction> iterator = walkway.iterator();
        go(iterator);
    }

    private void go(Iterator<Direction> walkway) {
        if (walkway.hasNext()) {
            final Direction direction = walkway.next();
            Tile next = getNextTile(direction);
            next.go(walkway);
        } else {
            black = !black;
        }
    }

    private Tile getNextTile(Direction direction) {
        switch (direction) {
            case E:
                return getTile(x + 2, y);
            case SE:
                return getTile(x + 1, y - 1);
            case SW:
                return getTile(x - 1, y - 1);
            case W:
                return getTile(x - 2, y);
            case NW:
                return getTile(x - 1, y + 1);
            case NE:
                return getTile(x + 1, y + 1);
            default:
                throw new IllegalArgumentException("No direction found");
        }
    }

    private Tile getTile(int x, int y) {
        final Tile tile = new Tile(x, y);
        if (STEPPED_ON.contains(tile)) {
            return STEPPED_ON.stream()
                    .filter(t -> t.equals(tile))
                    .findFirst()
                    .orElseThrow();
        }
        STEPPED_ON.add(tile);
        return tile;
    }

    static long getBlackTiles() {
        return STEPPED_ON.stream()
                .filter(t -> t.black)
                .count();
    }
}
