package de.argh.aoc.day08;

class CommandFacroty {
    public static Command create(String line) {
        String[] s = line.split(" ");
        switch (s[0]) {
            case "acc":
                return new AccCommand(Long.parseLong(s[1]));
            case "jmp":
                return new JmpCommand(Long.parseLong(s[1]));
            case "nop":
                return new NopCommand(Long.parseLong(s[1]));
            default:
                throw new IllegalStateException("errors in input; " + line);
        }
    }
}
