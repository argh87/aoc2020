package de.argh.aoc.day16;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AoCDay16 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input16.txt");

        TicketTranslator translator = new TicketTranslator(lines);

        translator.part_1();

        translator.part_2();
    }
}
