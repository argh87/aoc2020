package de.argh.aoc.day08;

interface Command {

    int execute(int index);

    boolean isSecondVisited();

    void reset();

    boolean isChangeable();

    Command change();
}
