package de.argh.aoc.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class TicketTranslator {

    final List<Rule> rules = new ArrayList<>();
    final Ticket myTicket;
    final List<Ticket> nearby = new ArrayList<>();

    TicketTranslator(List<String> lines) {
        int ti = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("")) {
                ti = i + 2;
                break;
            }
            rules.add(new Rule(lines.get(i)));
        }

        myTicket = new Ticket(lines.get(ti));
        for (int i = ti + 3; i < lines.size(); i++) {
            nearby.add(new Ticket(lines.get(i)));
        }
    }

    void part_1() {
        System.out.println(nearby.stream()
                .mapToInt(n -> n.getValuesNotApplyingRules(rules).stream().mapToInt(i -> i).sum())
                .sum());
    }

    void part_2() {
        List<Ticket> allValid = new ArrayList<>(nearby);
        allValid.removeIf(n -> !n.getValuesNotApplyingRules(rules).isEmpty());
        allValid.add(myTicket);

        Map<Integer, List<Rule>> mapping = new HashMap<>();

        for (int i = 0; i < myTicket.values.size(); i++) {
            int index = i;
            List<Rule> found = rules.stream()
                    .filter(r -> r.isMatchingTicketsOnValueIndex(allValid, index))
                    .collect(Collectors.toList());
            mapping.put(index, found);
        }

        while (!mapping.values().stream().allMatch(v -> v.size() == 1)) {
            mapping.values()
                    .stream()
                    .filter(v -> v.size() == 1)
                    .forEach(v -> {
                        mapping.values()
                                .stream()
                                .filter(r -> r.size() > 1)
                                .forEach(r -> r.remove(v.get(0)));
                    });
        }

        long produkt = 1;
        for (Map.Entry<Integer, List<Rule>> entry : mapping.entrySet()) {
            Rule rule = entry.getValue().get(0);
            if (rule.startsWith("departure ")) {
                produkt = produkt * myTicket.get(entry.getKey());
            }
        }

        System.out.println(produkt);
    }
}
