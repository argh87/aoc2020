package de.argh.aoc.day17;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AoCDay17 {
    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input17.txt");

        Grid grid = new Grid(lines);
        grid.part_1();

        HyperGrid hyperGrid = new HyperGrid(lines);
        hyperGrid.part_2();
    }
}
