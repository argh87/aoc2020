package de.argh.aoc.day07;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AoCDay7 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input07.txt");

        lines.forEach(l -> {
            String[] partitions = l.split(" bags contain ");
            String bagColor = partitions[0];

            Bags.add(bagColor, new Bag(bagColor, partitions[1]));
        });

        //part 1
        System.out.println(Bags.getBagsContaining("shiny gold").size());

        //part 2
        System.out.println(Bags.countingContent("shiny gold"));
    }
}
