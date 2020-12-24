package de.argh.aoc.day24;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AoCDay24 {

    public static void main(String[] args) {
        final List<String> lines = FileUtil.getLines("input24.txt");

        final Floor floor = new Floor(lines);

        floor.part_1();
    }
}
