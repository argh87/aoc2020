package de.argh.aoc.day22;

import java.util.ArrayList;
import java.util.List;

class Game {

    final List<Player> players = new ArrayList<>();
    final Player one;
    final Player two;

    Game(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i)
                    .startsWith("Player")) {
                final Player player = new Player(lines.get(i));
                for (int j = i + 1; j < lines.size(); j++) {
                    if (lines.get(j)
                            .equals("")) {
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

    void part_1() {
        while (nonePlayerLost()) {
            final Long drawOne = one.draw();
            final Long drawTwo = two.draw();
            if (drawOne.compareTo(drawTwo) > 0) {
                one.addCards(drawOne, drawTwo);
            } else {
                two.addCards(drawTwo, drawOne);
            }
        }

        players.stream()
                .filter(p -> !p.hasLost())
                .forEach(p -> System.out.println(p.getScore()));
    }

    private boolean nonePlayerLost() {
        return players.stream()
                .noneMatch(Player::hasLost);
    }
}
