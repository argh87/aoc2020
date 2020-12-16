package de.argh.aoc.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Ticket {

    final List<Integer> values = new ArrayList<>();

    Ticket(String ticket) {
        String[] values = ticket.split(",");
        Arrays.stream(values).forEach(v -> this.values.add(Integer.parseInt(v)));
    }

    List<Integer> getValuesNotApplyingRules(List<Field> fields) {
        return values.stream()
                .filter(v -> fields.stream()
                        .noneMatch(r -> r.contains(v)))
                .collect(Collectors.toList());
    }

    Integer get(int index) {
        return values.get(index);
    }
}
