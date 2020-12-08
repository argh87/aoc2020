package de.argh.aoc.day08;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AoCDay8 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input08.txt");
        Programm programm = new Programm(lines);

        // part 1
        programm.run_1();

        // part 1
        programm.run_2();
    }
}
