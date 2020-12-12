package de.argh.aoc.day12;

import java.util.ArrayList;
import java.util.List;

class Ship {

    List<Movement> commands = new ArrayList<>();

    Ship(List<String> commands) {
        commands.forEach(c -> this.commands.add(MovementFactory.create(c)));
    }

    Position part_1() {
        Position position = new Position();
        commands.forEach(c -> c.move(position));

        return position;
    }
}
