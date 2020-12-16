package de.argh.aoc.day16;

import java.util.List;

class Rule {

    final String name;
    final Range rangeL;
    final Range rangeR;

    Rule(String rule) {
        String[] keyValue = rule.split(": ");

        name = keyValue[0];
        String[] ranges = keyValue[1].split(" or ");

        rangeL = new Range(ranges[0]);
        rangeR = new Range(ranges[1]);
    }

    boolean contains(Integer value) {
        return rangeL.contains(value) || rangeR.contains(value);
    }

    boolean isMatchingTicketsOnValueIndex(List<Ticket> tickets, int index) {
        return tickets.stream().map(t -> t.get(index)).allMatch(this::contains);
    }

    public boolean startsWith(String term) {
        return name.startsWith(term);
    }
}
