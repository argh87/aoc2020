package de.argh.aoc.day06;

import java.util.HashSet;
import java.util.Set;

public class Person {
    final Set<Integer> answers = new HashSet<>();

    public Person(String questions) {
        questions.chars().forEach(answers::add);
    }

    public boolean hasAnswered(Integer question) {
        return answers.contains(question);
    }
}
