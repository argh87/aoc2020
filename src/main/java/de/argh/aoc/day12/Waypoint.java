package de.argh.aoc.day12;

class Waypoint {

    int south;
    int north;
    int west;
    int east;

    Waypoint(int north, int east, int south, int west) {
        this.south = south;
        this.north = north;
        this.west = west;
        this.east = east;
    }

    int getManhattenDistance() {
        return south + north + west + east;
    }

    void left() {
        int tmp = east;
        east = south;
        south = west;
        west = north;
        north = tmp;
    }

    void right() {
        int tmp = west;
        west = south;
        south = east;
        east = north;
        north = tmp;
    }

    void reverse() {
        int tmph = west;
        west = east;
        east = tmph;

        int tmpv = north;
        north = south;
        south = tmpv;
    }
}
