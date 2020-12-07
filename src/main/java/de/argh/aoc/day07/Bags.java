package de.argh.aoc.day07;

import java.util.*;

class Bags {

    static Map<String, Bag> existing = new HashMap<>();

    static void add(String bagColor, Bag bag) {
        existing.put(bagColor, bag);
    }

    static Set<String> getBagsContaining(String color) {
        Set<String> colors = new HashSet<>();
        for (Map.Entry<String, Bag> entry : existing.entrySet()) {
            Bag value = entry.getValue();
            if (value.contains(color)) {
                collect(entry.getKey(), colors);
            }
        }
        return colors;
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
     * calculates how many individual bags are required inside single colored bag
     *
     * @param color Color
     * @return Individual bags.
     */
    static long countingContent(String color) {
        Bag bag = existing.get(color);
        arrange(bag, new HashSet<>());

        return bag.getContainingBags() - 1;

    }

    private static void arrange(Bag bag, Set<String> counted) {
        Collection<Bag> containgColors = bag.getContaingColors();
        containgColors
                .forEach(b ->
                        {
                            counted.add(b.getColor());
                            Bag listed = existing.get(b.getColor());
                            if (!listed.isEmpty()) {
                                listed.getContaingColors().forEach(l -> {
                                    Bag r = existing.get(l.getColor());
                                    b.add(r, l.getCount());
                                    arrange(b, counted);
                                });
                            }
                        }
                );
    }
}
