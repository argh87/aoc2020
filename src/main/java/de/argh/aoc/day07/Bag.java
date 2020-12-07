package de.argh.aoc.day07;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class Bag {

    final private long count;
    final private String color;
    final private Map<String, Bag> containing = new HashMap<>();

    Bag(String bagColor, String partition) {
        this(1, bagColor, partition);
    }

    private Bag(int count, String bagColor, String partition) {
        this.color = bagColor;
        this.count = count;

        if (!partition.equals("no other bags.")) {
            String[] bags = partition.substring(0, partition.length() - 1).split(", ");

            for (String bag : bags) {
                String[] parts = bag.split(" ");
                int i = Integer.parseInt(parts[0]);
                String c = parts[1] + " " + parts[2];
                this.containing.put(c, new Bag(i, c, "no other bags."));
            }
        }
    }

    Bag(long count, Bag existing) {
        this.count = count;
        color = existing.color;
        containing.putAll(existing.containing);
    }

    long getCount() {
        return count;
    }

    String getColor() {
        return color;
    }

    boolean contains(String color) {
        return containing.containsKey(color);
    }

    Collection<Bag> getContaingColors() {
        return containing.values();
    }

    long getSum() {
        return containing.values().stream().mapToLong(bag -> bag.count).sum();
    }

    long getContainingBags() {
        if (containing.isEmpty()) {
            return 1;
        }

        long sum = 0;
        for (Bag value : Bags.getChildren(this)) {
            sum += value.getContainingBags() * value.getCount();
        }
        return sum + 1;
    }

    private static String repeat(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }

    boolean isEmpty() {
        return containing.isEmpty();
    }

    @Override
    public String toString() {
        return "Bag{" +
                "count=" + count +
                ", color='" + color + '\'' +
                ", containing=" + containing +
                '}';
    }
}
