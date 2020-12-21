package de.argh.aoc.day21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Ingredients {

    private final List<Food> foods = new ArrayList<>();
    private static final Map<String, Set<String>> ALLERGENES = new HashMap<>();

    Ingredients(List<String> lines) {
        lines.forEach(l -> foods.add(new Food(l)));
        init();
    }

    private void init() {
        for (String a : ALLERGENES.keySet()) {
            final List<Food> collect = foods.stream()
                    .filter(f -> f.allg.contains(a))
                    .collect(Collectors.toList());

            for (Food food : collect) {
                for (String ing : food.ings) {
                    if (collect.stream()
                            .allMatch(f -> f.ings.contains(ing))) {
                        ALLERGENES.get(a)
                                .add(ing);
                    }
                }
            }
        }

        // as long allergies are not unique to ingredient
        while (!ALLERGENES.values()
                .stream()
                .allMatch(s -> s.size() == 1)) {

            // For all allergies one to one
            ALLERGENES.values()
                    .stream()
                    .filter(s -> s.size() == 1)
                    .forEach(s -> s.forEach(a -> {
                        // remove from recipies non unique
                        ALLERGENES.values()
                                .stream()
                                .filter(t -> t.size() > 1 && t.contains(a))
                                .forEach(t -> t.remove(a));
                    }));
        }
    }

    void part_1() {
        Set<String> allAllergenes = ALLERGENES.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        long sum = 0;
        for (Food food : foods) {
            sum += food.without(allAllergenes)
                    .size();
        }
        System.out.println(sum);
    }

    void part_2() {
        System.out.println(ALLERGENES.entrySet()
                .stream()
                .map(e -> new Allergene(e.getValue()
                        .stream()
                        .findFirst()
                        .orElseThrow(), e.getKey()))
                .sorted(Comparator.comparing(a -> a.name))
                .map(a -> a.cryptic)
                .collect(Collectors.joining(",")));
    }

    private static class Allergene {
        final String cryptic;
        final String name;

        Allergene(String cryptic, String name) {
            this.cryptic = cryptic;
            this.name = name;
        }
    }

    private static class Food {
        Set<String> ings = new HashSet<>();
        Set<String> allg = new HashSet<>();

        Food(String toParse) {
            final String[] split = toParse.substring(0, toParse.length() - 1)
                    .split(" \\(contains ");

            ings.addAll(Arrays.asList(split[0].split(" ")));
            allg.addAll(Arrays.asList(split[1].split(", ")));
            allg.forEach(a -> ALLERGENES.put(a, new HashSet<>()));
        }

        Set<String> without(Set<String> allAllergenes) {
            Set<String> tmp = new HashSet<>(ings);
            tmp.removeAll(allAllergenes);
            return tmp;
        }
    }
}
