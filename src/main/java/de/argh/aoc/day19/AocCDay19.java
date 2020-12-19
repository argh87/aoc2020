package de.argh.aoc.day19;

import de.argh.aoc.FileUtil;

public class AocCDay19 {

    public static void main(String[] args) {
        Message message = new Message(FileUtil.getLines("input19.txt"));
        message.parse();
    }
}
