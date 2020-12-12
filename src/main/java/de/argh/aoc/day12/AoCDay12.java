package de.argh.aoc.day12;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AoCDay12 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input12.txt");

        Ship ship = new Ship(lines);

        System.out.println(ship.part_1()
                .getManhattenDistance());
    }
}
