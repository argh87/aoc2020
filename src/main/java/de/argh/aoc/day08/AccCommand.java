package de.argh.aoc.day08;

class AccCommand implements Command {

    private final long value;
    private int visited = 0;

    AccCommand(long value) {
        this.value = value;
    }

    @Override
    public int execute(int index) {
        visited++;
        if (!isSecondVisited()) {
            Programm.accumulator += value;
        }
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
        return false;
    }

    @Override
    public Command change() {
        return this;
    }
}
