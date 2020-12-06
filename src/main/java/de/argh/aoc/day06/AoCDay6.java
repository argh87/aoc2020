package de.argh.aoc.day06;

import de.argh.aoc.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class AoCDay6 {

    public static void main(String[] args) {

        List<String> lines = FileUtil.getLines("input06.txt");
        List<Group> groups = parsePassworts(lines);

        // part 1
        System.out.println(groups.stream()
                .mapToLong(Group::getQuestionsSize)
                .sum());

        // part 1
        System.out.println(groups.stream()
                .mapToLong(Group::getAnswerdByEveryone)
                .sum());
    }

    private static List<Group> parsePassworts(List<String> lines) {
        List<Group> groups = new ArrayList<>();
        final Group[] group = {new Group()};

        lines.forEach(l -> {
            if (l.trim()
                    .equals("")) {
                groups.add(group[0]);
                group[0] = new Group();
            } else {
                group[0].addPerson(l);
            }
        });
        groups.add(group[0]);
        return groups;
    }
}
