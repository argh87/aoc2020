package de.argh.aoc.day11;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AoCDay11 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input11.txt");

        Seats seats = new Seats(lines);

        seats.part_1();

        seats.part_2();
    }
}
