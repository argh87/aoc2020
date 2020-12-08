package de.argh.aoc.day08;

public class NopCommand implements Command {

    private final long value;
    private int visited = 0;

    public NopCommand(long value) {
        this.value = value;
    }

    @Override
    public int execute(int index) {
        visited++;
        return index + 1;
    }

    @Override
    public boolean isSecondVisited() {
        return visited == 2;
    }

    @Override
    public void reset() {
        visited = 0;
    }

    @Override
    public boolean isChangeable() {
        return true;
    }

    @Override
    public Command change() {
        return new JmpCommand(value);
    }
}
