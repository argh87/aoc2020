package de.argh.aoc.day10;

import de.argh.aoc.FileUtil;

public class AoCDay10 {

    public static void main(String[] args) {
        Joltage joltage = new Joltage(FileUtil.getLines("input10.txt"));

        System.out.println(joltage.part_1());

        System.out.println(joltage.part_2());
    }
}
