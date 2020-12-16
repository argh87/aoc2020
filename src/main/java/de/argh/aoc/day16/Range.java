package de.argh.aoc.day16;

class Range {

    final int lower;
    final int upper;

    Range(String range) {
        String[] bounds = range.split("-");
        lower = Integer.parseInt(bounds[0]);
        upper = Integer.parseInt(bounds[1]);
    }

    public boolean contains(Integer value) {
        return lower <= value && value <= upper;
    }
}
