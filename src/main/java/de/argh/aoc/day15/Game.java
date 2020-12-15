package de.argh.aoc.day15;

import java.util.List;

class Game {

    Turn turn;

    Game() {
    }

    private void initStartSequence(List<Long> starting) {
        turn = new Turn(starting.get(0));
        for (int i = 1; i < starting.size(); i++) {
            turn = turn.initNext(starting.get(i));
        }
    }

    void runUntil(List<Long> starting, long turnId) {
        Turn.cache.clear();
        initStartSequence(starting);
        while (turn.getId() < turnId) {
            turn = turn.next();
        }
        System.out.println(turn.spoken);
    }
}
