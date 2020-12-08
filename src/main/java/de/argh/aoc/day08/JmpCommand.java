package de.argh.aoc.day08;

public class JmpCommand implements Command {

    private final long value;
    private int visited = 0;

    public JmpCommand(long value) {
        this.value = value;
    }

    @Override
    public int execute(int index) {
        visited++;
        return (int) (index + value);
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
        return new NopCommand(value);
    }
}
