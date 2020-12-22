package de.argh.aoc.day22;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AoCDay22 {

    public static void main(String[] args) {
        final List<String> lines = FileUtil.getLines("input22.txt");
        Game game = new Game(lines);

        game.part_1();
    }
}
