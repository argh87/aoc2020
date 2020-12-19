package de.argh.aoc.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Rule {

    private final int id;
    private final String sub;
    private final List<Rule> left = new ArrayList<>();
    private final List<Rule> right = new ArrayList<>();
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
            for (String num : rs[0].split(" ")) {
                left.add(rules.get(Integer.valueOf(num)));
            }
            for (String num : rs[1].split(" ")) {
                right.add(rules.get(Integer.valueOf(num)));
            }
        } else {
            for (String num : sub.split(" ")) {
                left.add(rules.get(Integer.valueOf(num)));
            }
        }
    }

    String getRegex() {
        if (isChar) {
            return String.valueOf(c);
        }

        if (id == 0) {
            String all = this.left.stream()
                    .map(Rule::getRegex)
                    .collect(Collectors.joining());
            return "^" + all + "+$";
        }

        String left = this.left.stream()
                .map(Rule::getRegex)
                .collect(Collectors.joining());

        if (right.isEmpty()) {
            return "(" + left + ")";
        }

        String right = this.right.stream()
                .map(Rule::getRegex)
                .collect(Collectors.joining());

        return "(" + left + "|" + right + ")";
    }
}
