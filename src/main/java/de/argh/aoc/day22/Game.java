package de.argh.aoc.day22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Game {

    final List<Player> players = new ArrayList<>();
    Player one;
    Player two;

    private void init(List<String> lines) {
        players.clear();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i)
                    .startsWith("Player")) {
                final Player player = new Player(lines.get(i));
                for (int j = i + 1; j < lines.size(); j++) {
                    if ("".equals(lines.get(j))) {
                        break;
                    }
                    player.addCard(lines.get(j));
                }
                players.add(player);
            }
        }
        one = players.get(0);
        two = players.get(1);
    }

    void part_1(List<String> lines) {
        init(lines);
        while (nonePlayerLost()) {
            final Long drawOne = one.draw();
            final Long drawTwo = two.draw();
            giveWinnerCards(one, two, drawOne, drawTwo, drawOne.compareTo(drawTwo) > 0);
        }

        players.stream()
                .filter(p -> !p.hasLost())
                .forEach(p -> System.out.println(p.getScore()));
    }

    private boolean nonePlayerLost() {
        return players.stream()
                .noneMatch(Player::hasLost);
    }

    void part_2(List<String> lines) {
        init(lines);
        final Player winner = recursiveGame(one, two, 1);
        System.out.println(winner.getScore());
    }

    private Player recursiveGame(Player one, Player two, int depth) {
        List<Player> ps = Arrays.asList(one, two);

        final Set<Combi> dealt = new HashSet<>();
        while (nonePlayerLost(ps)) {
            final Combi combi = new Combi(one, two);
            if (dealt.contains(combi)) {
                return one;
            }
            dealt.add(combi);

            final Long drawOne = one.draw();
            final Long drawTwo = two.draw();

            if (isStartingSubGame(one, two, drawOne, drawTwo)) {
                final Player winner = recursiveGame(one.copy(drawOne), two.copy(drawTwo), depth + 1);
                giveWinnerCards(one, two, drawOne, drawTwo, one.is(winner));
            } else {
                giveWinnerCards(one, two, drawOne, drawTwo, drawOne.compareTo(drawTwo) > 0);
            }
        }
        return ps.stream()
                .filter(p -> !p.hasLost())
                .findFirst()
                .orElseThrow();
    }

    private void giveWinnerCards(Player one, Player two, Long drawOne, Long drawTwo, boolean oneWinning) {
        if (oneWinning) {
            one.addCards(drawOne, drawTwo);
        } else {
            two.addCards(drawTwo, drawOne);
        }
    }

    private boolean isStartingSubGame(Player one, Player two, Long drawOne, Long drawTwo) {
        return drawOne.compareTo(one.deckSize()) < 0 && drawTwo.compareTo(two.deckSize()) < 0;
    }

    private boolean nonePlayerLost(List<Player> ps) {
        return ps.stream()
                .noneMatch(Player::hasLost);
    }

}
