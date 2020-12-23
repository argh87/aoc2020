package de.argh.aoc.day23;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Clockwise {

    private final List<Integer> cups;

    Clockwise(List<Integer> cups) {
        this.cups = cups;
    }

    void part_1() {
        final int max = 9;
        final int rounds = 100;

        final Cup one = play(max, rounds, Cup.of(cups));

        one.print(one);
        System.out.println();
    }

    private Cup play(int max, int rounds, Cup start) {
        Map<Integer, Cup> map = start.asMap();

        Cup current = start;
        for (int i = 0; i < rounds; i++) {
            Integer value = current.value;
            Cup nextThree = current.getNextThree();
            do {
                value = value - 1 == 0 ? max : value - 1;
            } while (nextThree.contains(nextThree, value));
            final Cup cup = map.get(value);
            cup.insertTaken(nextThree);
            current = current.next;
        }

        return map.get(1);
    }

    void part_2() {
        final int max = 1000000;
        final int rounds = 10000000;

        final Cup one = play(max, rounds, Cup.of(cups, max - cups.size()));

        final Cup starOne = one.next;
        final Cup starTwo = starOne.next;
        System.out.println((long) (starOne.value) * starTwo.value);
    }

    private static class Cup {
        boolean root;
        Integer value;
        Cup next;

        private Cup(Integer value, boolean root) {
            this.value = value;
            this.root = root;
        }

        private Cup(Integer value) {
            this.value = value;
        }

        static Cup of(List<Integer> cups) {
            final Cup root = new Cup(cups.get(0), true);
            Cup lastCup = root;
            for (int i = 1; i < cups.size(); i++) {
                lastCup = root.stack(cups.get(i), lastCup);
            }
            lastCup.next = root;
            return root;
        }

        static Cup of(List<Integer> cups, int additional) {
            final Cup root = new Cup(cups.get(0), true);
            Cup lastCup = root;
            for (int i = 1; i < cups.size(); i++) {
                lastCup = root.stack(cups.get(i), lastCup);
            }

            for (int i = cups.size() + 1; i < additional + cups.size() + 1; i++) {
                lastCup = root.stack(i, lastCup);
            }

            lastCup.next = root;
            return root;
        }

        private Cup stack(Integer value, Cup lastCup) {
            final Cup cup = new Cup(value);
            lastCup.next = cup;
            return cup;
        }

        Cup getNextThree() {
            final Cup next3 = this.next;
            this.next = next.next.next.next;

            next3.next.next.next = null;
            return next3;
        }

        boolean contains(Cup start, Integer value) {
            if (value.equals(this.value)) {
                return true;
            }
            if (next != null && !start.equals(next)) {
                return next.contains(start, value);
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Cup cup = (Cup) o;
            return value.equals(cup.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        Map<Integer, Cup> asMap() {
            Map<Integer, Cup> asMap = new HashMap<>();
            asMap.put(value, this);

            Cup elem = this.next;
            while (!elem.root) {
                asMap.put(elem.value, elem);
                elem = elem.next;
            }
            return asMap;
        }

        public void insertTaken(Cup three) {
            final Cup tmp = this.next;
            next = three;

            final Cup tLast = three.getLast();
            tLast.next = tmp;
        }

        private Cup getLast() {
            if (next != null) {
                return next.getLast();
            }
            return this;
        }

        void print(Cup start) {
            System.out.print(next.value);
            if (next != null && !start.equals(next)) {
                next.print(start);
            }
        }
    }
}
