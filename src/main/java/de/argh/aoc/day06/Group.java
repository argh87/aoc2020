package de.argh.aoc.day06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {

    final List<Person> persons = new ArrayList<>();
    final Set<Integer> answers = new HashSet<>();

    public void addPerson(String person) {
        person.chars()
                .forEach(answers::add);
        persons.add(new Person(person));
    }

    long getQuestionsSize() {
        return answers.size();
    }

    long getAnswerdByEveryone() {
        return answers.stream()
                .filter(q -> persons.stream()
                        .allMatch(p -> p.hasAnswered(q)))
                .count();
    }
}
