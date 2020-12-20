package de.argh.aoc.day20;

import de.argh.aoc.FileUtil;

public class AoCDay20 {

    public static void main(String[] args) {
        Image image = new Image(FileUtil.getLines("input20.txt"));

        image.part_1();

    }
}
