package de.argh.aoc.day18;

import de.argh.aoc.FileUtil;

import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

public class AoCDay18 {

    public static void main(String[] args) throws ScriptException {
        List<String> lines = FileUtil.getLines("input18.txt");

        part_1(lines);

        part_2(lines);
    }

    private static void part_1(List<String> lines) {
        Operator add = new Operator("+", 0, Long::sum);
        Operator mul = new Operator("*", 0, (d1, d2) -> d1 * d2);

        ExpressionParser equalPrecendence = new ExpressionParser(Arrays.asList(add, mul));
        parseInput(lines, equalPrecendence);
    }

    private static void part_2(List<String> lines) {
        Operator add = new Operator("+", 1, Long::sum);
        Operator mul = new Operator("*", 0, (d1, d2) -> d1 * d2);

        ExpressionParser reversePrecendence = new ExpressionParser(Arrays.asList(add, mul));
        parseInput(lines, reversePrecendence);
    }

    private static void parseInput(List<String> lines, ExpressionParser reversePrecendence) {
        Long sum = 0L;
        for (String line : lines) {
            long result = reversePrecendence.parseAsLong(line.split(""));
            sum += result;
        }
        System.out.println(sum);
    }
}
