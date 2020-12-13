package de.argh.aoc.day13;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AocDay13 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input13.txt");

        Schedule schedule = new Schedule(lines);
        Schedule.Bus nextBus = schedule.getNextBus();

        System.out.println(nextBus.id * nextBus.getTimeAfterMinutes());
    }
}

