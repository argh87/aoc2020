package de.argh.aoc.day12;

class Position {

    Direction facing = Direction.EAST;
    int south = 0;
    int north = 0;
    int west = 0;
    int east = 0;

    int getManhattenDistance() {
        return south + north + west + east;
    }
}
