package de.argh.aoc.day06;

import java.util.HashSet;
import java.util.Set;

class Person {
    final Set<Integer> answers = new HashSet<>();

    Person(String questions) {
        questions.chars()
                .forEach(answers::add);
    }

    boolean hasAnswered(Integer question) {
        return answers.contains(question);
    }
}
