package de.argh.aoc.day15;

import java.util.Arrays;
import java.util.List;

public class AoCDay15 {

    public static void main(String[] args) {
        Game game = new Game();

        List<Long> starting = Arrays.asList(0L, 1L, 5L, 10L, 3L, 12L, 19L);

        // part 1
        game.runUntil(starting, 2020L);

        // part 2
        game.runUntil(starting, 30000000L);
    }


}
