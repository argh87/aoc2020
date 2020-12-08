package de.argh.aoc.day08;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Programm {

    private final List<Command> sequence = new ArrayList<>();

    static long accumulator = 0;

    Programm(List<String> lines) {
        sequence.addAll(lines.stream().map(CommandFacroty::create).collect(Collectors.toList()));
    }

    void run_1() {
        accumulator = 0;
        int index = 0;
        while (sequence.stream().noneMatch(Command::isSecondVisited)) {
            Command c = sequence.get(index);
            index = c.execute(index);
        }
        System.out.println(accumulator);
    }

    public void run_2() {
        int index = 0;
        int lastChanged = -1;

        while (index < sequence.size()) {
            sequence.forEach(Command::reset);
            lastChanged = changeNopOrJumpCommandNextTo(lastChanged);
            index = innerRun();
        }

        System.out.println(accumulator);
    }

    private int innerRun() {
        accumulator = 0;
        int index = 0;
        while (index < sequence.size() && sequence.stream().noneMatch(Command::isSecondVisited)) {
            Command c = sequence.get(index);
            index = c.execute(index);
        }

        return index;
    }

    private int changeNopOrJumpCommandNextTo(int index) {
        if (index >= 0) {
            sequence.set(index, sequence.get(index).change());
        }

        int next = getNextIndexOfNopOrJumpCommandTo(index);
        System.out.println("changing " + next);
        sequence.set(next, sequence.get(next).change());
        return next;
    }

    private int getNextIndexOfNopOrJumpCommandTo(int index) {
        for (int i = index + 1; i < sequence.size(); i++) {
            Command command = sequence.get(i);
            if (command.isChangeable()) {
                return i;
            }
        }
        throw new IllegalStateException("there is no next");
    }
}
