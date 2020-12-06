package de.argh.aoc.day06;

import java.util.HashSet;
import java.util.Set;

public class Person {
    final Set<Integer> ansers = new HashSet<>();

    public Person(String questions) {
        questions.chars().forEach(ansers::add);
    }

    public boolean anwered(Integer question) {
        return ansers.contains(question);
    }
}
