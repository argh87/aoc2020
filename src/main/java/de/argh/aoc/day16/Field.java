package de.argh.aoc.day16;

import java.util.ArrayList;
import java.util.List;

class Field {

    final String name;
    final List<Range> ranges = new ArrayList<>();

    Field(String rule) {
        String[] keyValue = rule.split(": ");

        name = keyValue[0];
        String[] ranges = keyValue[1].split(" or ");
        for (String range : ranges) {
            this.ranges.add(new Range(range));
        }
    }

    boolean contains(Integer value) {
        return ranges.stream()
                .anyMatch(r -> r.contains(value));
    }

    public boolean startsWith(String term) {
        return name.startsWith(term);
    }
}
