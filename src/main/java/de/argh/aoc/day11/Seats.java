package de.argh.aoc.day11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class Seats {

    final List<String> lines = new ArrayList<>();

    static int maxY;
    static int maxX;
    static Map<Point, Seat> seats = new LinkedHashMap<>();

    Seats(List<String> lines) {
        this.lines.addAll(lines);
        maxY = lines.size();
        maxX = lines.get(0)
                .length();
    }

    private void initSet() {
        seats.clear();
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                Point point = new Point(x, y);
                seats.put(point, new Seat(point, lines.get(y)
                        .charAt(x)));
            }
        }
    }

    void part_1() {
        initSet();
        Collection<Seat> values = seats.values();
        values.forEach(Seat::findNextAdjacent);

        long occupied = switchStates(values, 4);

        while (isAnySwitched(values)) {
            occupied = switchStates(values, 4);
        }

        System.out.println(occupied);
    }

    void part_2() {
        initSet();
        Collection<Seat> values = seats.values();
        values.forEach(Seat::findNearestAdjacents);

        long occupied = switchStates(values, 5);

        while (isAnySwitched(values)) {
            occupied = switchStates(values, 5);
        }

        System.out.println(occupied);
    }

    private boolean isAnySwitched(Collection<Seat> values) {
        return values.stream()
                .anyMatch(s -> s.switched);
    }

    private long switchStates(Collection<Seat> values, int maxOccupied) {
        values.forEach(seat -> seat.validateState(maxOccupied));
        values.forEach(Seat::switchState);

        return values.stream()
                .filter(s -> s.checkState.equals(State.OCCUPIED))
                .count();
    }

    static class Point {
        final int x;
        final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

    }

    static class Seat {

        boolean switched;
        final Point p;
        State checkState;
        State currentState;
        Set<Seat> adjacents = new HashSet<>();

        Seat(Point point, char checkState) {
            this.p = point;
            this.checkState = checkState == 'L' ? State.EMPTY : State.FLOOR;
            this.currentState = this.checkState;
        }

        void addAdjacent(Seat seat) {
            adjacents.add(seat);
        }

        void findNextAdjacent() {
            adjacents.clear();
            if (p.y > 0) {
                addAdjacent(seats.get(new Point(p.x, p.y - 1))); // upper
                if (p.x > 0) {
                    addAdjacent(seats.get(new Point(p.x - 1, p.y - 1))); // upper left
                }
            }
            if (p.y < maxY - 1) {
                addAdjacent(seats.get(new Point(p.x, p.y + 1))); // lower
                if (p.x < maxX - 1) {
                    addAdjacent(seats.get(new Point(p.x + 1, p.y + 1))); // lower right
                }
            }

            if (p.x > 0) {
                addAdjacent(seats.get(new Point(p.x - 1, p.y))); // left
                if (p.y < maxY - 1) {
                    addAdjacent(seats.get(new Point(p.x - 1, p.y + 1))); // lower left
                }
            }
            if (p.x < maxX - 1) {
                addAdjacent(seats.get(new Point(p.x + 1, p.y))); // right
                if (p.y > 0) {
                    addAdjacent(seats.get(new Point(p.x + 1, p.y - 1))); // upper right
                }
            }
        }

        void findNearestAdjacents() {
            adjacents.clear();
            getUpperLeft();
            getUpper();
            getUpperRight();
            getRight();
            getLowerRight();
            getLower();
            getLowerLeft();
            getLeft();
        }

        private void getUpperLeft() {
            for (int x = p.x - 1, y = p.y - 1; x >= 0 && y >= 0; x--, y--) {
                Seat seat = seats.get(new Point(x, y));
                if (!seat.checkState.equals(State.FLOOR)) {
                    adjacents.add(seat);
                    return;
                }
            }
        }

        private void getUpper() {
            for (int y = p.y - 1; y >= 0; y--) {
                Seat seat = seats.get(new Point(p.x, y));
                if (!seat.checkState.equals(State.FLOOR)) {
                    adjacents.add(seat);
                    return;
                }
            }
        }

        private void getUpperRight() {
            for (int x = p.x + 1, y = p.y - 1; x < maxX && y >= 0; x++, y--) {
                Seat seat = seats.get(new Point(x, y));
                if (!seat.checkState.equals(State.FLOOR)) {
                    adjacents.add(seat);
                    return;
                }
            }
        }

        private void getRight() {
            for (int x = p.x + 1; x < maxX; x++) {
                Seat seat = seats.get(new Point(x, p.y));
                if (!seat.checkState.equals(State.FLOOR)) {
                    adjacents.add(seat);
                    return;
                }
            }
        }

        private void getLowerRight() {
            for (int x = p.x + 1, y = p.y + 1; x < maxX && y < maxY; x++, y++) {
                Seat seat = seats.get(new Point(x, y));
                if (!seat.checkState.equals(State.FLOOR)) {
                    adjacents.add(seat);
                    return;
                }
            }
        }

        private void getLower() {
            for (int y = p.y + 1; y < maxY; y++) {
                Seat seat = seats.get(new Point(p.x, y));
                if (!seat.checkState.equals(State.FLOOR)) {
                    adjacents.add(seat);
                    return;
                }
            }
        }

        private void getLowerLeft() {
            for (int x = p.x - 1, y = p.y + 1; x >= 0 && y < maxY; x--, y++) {
                Seat seat = seats.get(new Point(x, y));
                if (!seat.checkState.equals(State.FLOOR)) {
                    adjacents.add(seat);
                    return;
                }
            }
        }

        private void getLeft() {
            for (int x = p.x - 1; x >= 0; x--) {
                Seat seat = seats.get(new Point(x, p.y));
                if (!seat.checkState.equals(State.FLOOR)) {
                    adjacents.add(seat);
                    return;
                }
            }
        }

        void validateState(int maxOccupied) {
            switch (currentState) {
                case EMPTY:
                    if (adjacents.stream()
                            .noneMatch(s -> s.checkState.equals(State.OCCUPIED))) {
                        currentState = State.OCCUPIED;
                    }
                    break;
                case OCCUPIED:
                    if (adjacents.stream()
                            .filter(s -> s.checkState.equals(State.OCCUPIED))
                            .count() >= maxOccupied) {
                        currentState = State.EMPTY;
                    }
                    break;
                default:
                    // NOOP
            }
        }

        void switchState() {
            switched = !checkState.equals(currentState);
            checkState = currentState;
        }

        @Override
        public String toString() {
            return currentState.equals(State.FLOOR) ? "." : currentState.equals(State.OCCUPIED) ? "#" : "L";
        }
    }

    enum State {
        EMPTY, FLOOR, OCCUPIED;
    }
}
