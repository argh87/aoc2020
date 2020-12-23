package de.argh.aoc.day23;

import java.util.Arrays;

public class AoCDay23 {

    public static void main(String[] args) {
        final Clockwise clockwise = new Clockwise(Arrays.asList(4, 6, 7, 5, 2, 8, 1, 9, 3));

        clockwise.part_1();

        clockwise.part_2();
    }
}
