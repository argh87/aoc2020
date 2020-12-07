package de.argh.aoc.day07;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Bags {

    static Map<String, Bag> existing = new HashMap<>();

    static void add(String color, Bag bag) {
        existing.put(color, bag);
    }

    static long getBagsContaining(String color) {
        Set<String> colors = new HashSet<>();
        for (Map.Entry<String, Bag> entry : existing.entrySet()) {
            Bag value = entry.getValue();
            if (value.contains(color)) {
                collect(entry.getKey(), colors);
            }
        }
        return colors.size();
    }

    private static void collect(String color, Set<String> found) {
        found.add(color);

        for (Map.Entry<String, Bag> entry : existing.entrySet()) {
            Bag value = entry.getValue();
            if (value.contains(color)) {
                collect(entry.getKey(), found);
            }
        }
    }

    /**
     * Which bag has the most individual bags inside?
     *
     * @return the winner
     */
    static long getHighestContent() {
        return existing.keySet()
                .stream()
                .mapToLong(Bags::countingContent)
                .max()
                .orElse(0);
    }

    /**
     * calculates how many individual bags are required inside single colored bag
     *
     * @param color Color
     * @return Individual bags.
     */
    static long countingContent(String color) {
        Bag bag = existing.get(color);
        return bag.getContainingBags() - 1;

    }

    static Set<Bag> getChildren(Bag bag) {
        return bag.getContaingColors()
                .stream()
                .map(c -> new Bag(c.getCount(), existing.get(c.getColor())))
                .collect(Collectors.toSet());
    }
}
