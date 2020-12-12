package de.argh.aoc.day12;

class Position {

    Direction facing;
    Waypoint waypoint;

    Position(Direction facing) {
        this.facing = facing;
        this.waypoint = new Waypoint(0, 0, 0, 0);
    }

    int getManhattenDistance() {
        return waypoint.getManhattenDistance();
    }
}
