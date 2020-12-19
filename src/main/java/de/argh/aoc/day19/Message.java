package de.argh.aoc.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Message {

    private final Map<Integer, Rule> rules = new HashMap<>();
    private final List<String> messages = new ArrayList<>();

    Message(List<String> lines) {
        Iterator<String> it = lines.iterator();

        String line = it.next();
        while (!line.equals("")) {
            Rule rule = new Rule(line);
            rules.put(rule.getId(), rule);
            line = it.next();
        }

        rules.values()
                .forEach(r -> r.init(rules));

        while (it.hasNext()) {
            messages.add(it.next());
        }
    }

    void parse() {
        Rule rule = rules.get(0);
        String regex = rule.getRegex();
        System.out.println(messages.stream()
                .filter(m -> m.matches(regex))
                .count());
    }
}
