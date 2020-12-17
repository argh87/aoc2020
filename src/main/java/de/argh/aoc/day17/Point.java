package de.argh.aoc.day17;

import java.util.Objects;

class Point {
    final private int x;
    final private int y;
    final private int z;

    Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point point = (Point) o;
        return x == point.x && y == point.y && z == point.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public boolean isNext(Point other) {
        return isNext(x, other.x) && isNext(y, other.y) && isNext(z, other.z);
    }

    private boolean isNext(int p, int op) {
        return Math.abs(p - op) <= 1;
    }
}