package de.argh.aoc.day22;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Player {

    final String name;
    final List<Long> cards = new ArrayList<>();

    Player(String name) {
        this.name = name;
    }

    void addCard(String card) {
        cards.add(Long.parseLong(card));
    }

    private void addCard(Long card) {
        cards.add(card);
    }

    boolean hasLost() {
        return cards.isEmpty();
    }

    Long draw() {
        final Long drawn = cards.get(0);
        cards.remove(0);
        return drawn;
    }

    void addCards(Long own, Long competitor) {
        addCard(own);
        addCard(competitor);
    }

    Long getScore() {
        long sum = 0;
        for (int i = cards.size() - 1, c = 1; i >= 0; i--, c++) {
            sum += cards.get(i) * c;
        }

        return sum;
    }

    Long deckSize() {
        return (long) cards.size() + 1;
    }

    Player copy(Long drawOne) {
        final Player player = new Player(name);
        for (int i = 0; i < drawOne; i++) {
            player.cards.add(cards.get(i));
        }
        return player;
    }

    boolean is(Player winner) {
        return name.equals(winner.name);
    }

    String getDeckAsString() {
        return cards.stream()
                .map(s -> String.valueOf(s.longValue()))
                .collect(Collectors.joining(","));
    }
}
