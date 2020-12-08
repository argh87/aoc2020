package de.argh.aoc.day08;

public interface Command {

    int execute(int index);

    boolean isSecondVisited();

    void reset();

    boolean isChangeable();

    Command change();
}
