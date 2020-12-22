package de.argh.aoc.day22;

import java.util.ArrayList;
import java.util.List;

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
}
