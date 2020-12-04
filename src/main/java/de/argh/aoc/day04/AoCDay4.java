package de.argh.aoc.day04;

import de.argh.aoc.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class AoCDay4 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input04.txt");
        List<Passport> passports = parsePassworts(lines);

        System.out.println(passports.stream().filter(Passport::isValidFields).count());
        System.out.println(passports.stream().filter(Passport::isValidContent).count());
    }

    private static List<Passport> parsePassworts(List<String> lines) {
        List<Passport> passports = new ArrayList<>();
        final Passport[] passport = {new Passport()};

        lines.forEach(l -> {
            if (l.trim().equals("")) {
                passports.add(passport[0]);
                passport[0] = new Passport();
            } else {
                passport[0].addLine(l);
            }
        });
        passports.add(passport[0]);
        return passports;
    }
}
