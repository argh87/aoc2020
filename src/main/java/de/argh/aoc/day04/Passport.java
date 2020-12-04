package de.argh.aoc.day04;

import java.util.Arrays;
import java.util.List;

class Passport {
    String passwortLine = "";
    PassportValueContainer container;

    void addLine(String line) {
        passwortLine += line + " ";
    }

    boolean isValidFields() {
        if (container == null) {
            container = new PassportValueContainer(passwortLine);
        }
        return container.isValidFields();
    }

    boolean isValidContent() {
        if (container == null) {
            container = new PassportValueContainer(passwortLine);
        }
        return container.isValidContent();
    }

    private static class PassportValueContainer {
        private static final List<String> ecls = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

        final String byr;
        final String iyr;
        final String eyr;
        final String hgt;
        final String hcl;
        final String ecl;
        final String pid;
        final String cid;

        PassportValueContainer(String passportLine) {
            String[] parts = passportLine.split(" ");
            byr = get("byr", parts);
            iyr = get("iyr", parts);
            eyr = get("eyr", parts);
            hgt = get("hgt", parts);
            hcl = get("hcl", parts);
            ecl = get("ecl", parts);
            pid = get("pid", parts);
            cid = get("cid", parts);
        }

        private String get(String ident, String[] parts) {
            return Arrays.stream(parts)
                    .map(l -> l.split(":"))
                    .filter(p -> ident.equals(p[0]))
                    .map(p -> p[1]).findFirst()
                    .orElse(null);
        }

        boolean isValidFields() {
            return
                    byr != null && iyr != null && eyr != null && hgt != null &&
                            hcl != null && ecl != null && pid != null;
        }

        boolean isValidContent() {
            try {
                return isValidFields() && isByrValid() && isIyrValid() && isEyrValid() &&
                        isHgtValid() && isHclValid() && isEclValid() && isPidValid();

            } catch (NumberFormatException e) {
                return false;
            }
        }

        private boolean isByrValid() {
            int year = Integer.parseInt(byr);
            return between(year, 1920, 2002);
        }

        private boolean between(int year, int min, int max) {
            return year >= min && year <= max;
        }

        private boolean isIyrValid() {
            int year = Integer.parseInt(iyr);
            return between(year, 2010, 2020);
        }

        private boolean isEyrValid() {
            int year = Integer.parseInt(eyr);
            return between(year, 2020, 2030);
        }

        private boolean isHgtValid() {
            if (hgt.endsWith("cm")) {
                int cms = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                return between(cms, 150, 193);
            } else if (hgt.endsWith("in")) {
                int ins = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                return between(ins, 59, 76);
            }
            return false;
        }

        private boolean isHclValid() {
            return hcl.length() == 7 && hcl.charAt(0) == '#' && hcl.substring(1, 7).matches("^[0-9a-f]+$");
        }

        private boolean isEclValid() {
            return ecls.contains(ecl);
        }

        private boolean isPidValid() {
            return pid.length() == 9 && pid.matches("^[0-9]+$");
        }

        @Override
        public String toString() {
            return "PassportValueContainer{" +
                    "byr='" + byr + '\'' +
                    ", iyr='" + iyr + '\'' +
                    ", eyr='" + eyr + '\'' +
                    ", hgt='" + hgt + '\'' +
                    ", hcl='" + hcl + '\'' +
                    ", ecl='" + ecl + '\'' +
                    ", pid='" + pid + '\'' +
                    ", cid='" + cid + '\'' +
                    '}';
        }
    }
}
