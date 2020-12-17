package de.argh.aoc.day17;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Cube {

    Point pos;
    boolean active;
    boolean nextActive;
    Set<Cube> neighbors = new HashSet<>();

    Cube(Point pos, boolean active) {
        this.pos = pos;
        this.active = active;
        this.nextActive = active;
    }

    boolean isNeighbor(Cube other) {
        return !this.equals(other) && pos.isNext(other.pos);
    }

    void prepareActive() {
        int amount = activeNeighbors();
        if (active) {
            if (!(amount == 2 || amount == 3)) {
                nextActive = false;
            }
        } else {
            if (amount == 3) {
                nextActive = true;
            }
        }
    }

    private int activeNeighbors() {
        return (int) neighbors.stream()
                .filter(n -> n.active)
                .count();
    }

    void changeActive() {
        active = nextActive;
    }

    boolean isActive() {
        return active;
    }

    void findNeighbors(Collection<Cube> dimension) {
        if (neighbors.size() == 26) {
            return;
        }
        dimension.stream()
                .filter(this::isNeighbor)
                .forEach(dp -> neighbors.add(dp));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cube cube = (Cube) o;
        return pos.equals(cube.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos);
    }

}
