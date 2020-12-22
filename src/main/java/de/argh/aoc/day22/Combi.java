package de.argh.aoc.day22;

import java.util.Objects;

class Combi {
    final String one;
    final String two;

    Combi(Player one, Player two) {
        this.one = one.getDeckAsString();
        this.two = two.getDeckAsString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Combi combi = (Combi) o;
        return one.equals(combi.one) && two.equals(combi.two);
    }

    @Override
    public int hashCode() {
        return Objects.hash(one, two);
    }
}
