package de.argh.aoc.day17;

import java.util.Objects;

class Point {
    final private int x;
    final private int y;
    final private int z;
    final private int w;

    Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 0;
    }

    Point(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    int getW() {
        return w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Point point = (Point) o;
        return x == point.x && y == point.y && z == point.z && w == point.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    boolean isNext(Point other) {
        return isNext(x, other.x) && isNext(y, other.y) && isNext(z, other.z);
    }

    boolean isHyperNext(Point other) {
        return isNext(x, other.x) && isNext(y, other.y) && isNext(z, other.z) && isNext(w, other.w);
    }

    private boolean isNext(int p, int op) {
        return Math.abs(p - op) <= 1;
    }
}