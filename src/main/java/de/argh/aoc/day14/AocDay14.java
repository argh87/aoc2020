package de.argh.aoc.day14;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AocDay14 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input14.txt");

        Emulator emulator = new Emulator(lines);

        System.out.println(emulator.part_1());

        System.out.println(emulator.part_2());
    }
}
