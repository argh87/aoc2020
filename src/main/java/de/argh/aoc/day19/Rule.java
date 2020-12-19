package de.argh.aoc.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Rule {

    private final int id;
    private final String sub;
    private final List<List<Rule>> ors = new ArrayList<>();
    private final boolean isChar;
    private final char c;

    Rule(String line) {
        String[] values = line.split(": ");
        id = Integer.parseInt(values[0]);
        sub = values[1];
        isChar = sub.equals("\"a\"") || sub.equals("\"b\"");
        c = sub.equals("\"a\"") ? 'a' : 'b';
    }

    Integer getId() {
        return id;
    }

    void init(Map<Integer, Rule> rules) {
        if (isChar) {
            return;
        }

        if (sub.contains("|")) {
            String[] rs = sub.split(" \\| ");
            List<Rule> left = new ArrayList<>();
            for (String num : rs[0].split(" ")) {
                left.add(rules.get(Integer.valueOf(num)));
            }
            ors.add(left);
            List<Rule> right = new ArrayList<>();
            for (String num : rs[1].split(" ")) {
                right.add(rules.get(Integer.valueOf(num)));
            }
            ors.add(right);
        } else {
            List<Rule> left = new ArrayList<>();
            for (String num : sub.split(" ")) {
                left.add(rules.get(Integer.valueOf(num)));
            }
            ors.add(left);
        }
    }

    String getRegex() {
        if (isChar) {
            return String.valueOf(c);
        }

        if (id == 0) {
            String all = ors.get(0)
                    .stream()
                    .map(Rule::getRegex)
                    .collect(Collectors.joining());
            return "^" + all + "+$";
        }

        if (id == 11 && !Message.PART1) {
            String inner = ors.stream()
                    .map(l -> l.stream()
                            .map(Rule::getRegex)
                            // referencing group e
                            .collect(Collectors.joining("\\k<e>*")))
                    .collect(Collectors.joining());

            // Naming group e
            return "(?<e>" + inner + ")+";
        }

        String inner = ors.stream()
                .map(l -> l.stream()
                        .map(Rule::getRegex)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("|"));

        if (id == 8 && !Message.PART1) {
            // Just repeat with plus
            return "(?:" + inner + ")+";
        }
        return "(" + inner + ")";
    }
}
