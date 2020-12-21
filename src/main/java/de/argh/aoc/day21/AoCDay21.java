package de.argh.aoc.day21;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AoCDay21 {

    public static void main(String[] args) {
        final List<String> lines = FileUtil.getLines("input21.txt");

        Ingredients ingredients = new Ingredients(lines);

        ingredients.part_1();

        ingredients.part_2();
    }
}
