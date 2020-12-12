package de.argh.aoc.day12;

interface Movement {

    void move(Position position);

    void move(Position position, Waypoint waypoint);
}
