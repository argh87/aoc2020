package de.argh.aoc.day09;

import de.argh.aoc.FileUtil;

public class AoCDay9 {

    public static void main(String[] args) {
        XMas xMas = new XMas(FileUtil.getLines("input09.txt"));

        // part 1
        System.out.println(xMas.findError());

        // part 2
        System.out.println(xMas.getErrorRange());
    }
}
