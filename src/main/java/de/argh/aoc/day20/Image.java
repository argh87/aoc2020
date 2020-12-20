package de.argh.aoc.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Image {

    List<Tile> tiles = new ArrayList<>();

    Image(List<String> lines) {
        for (int i = 0; i < lines.size(); i += 12) {
            String line = lines.get(i);

            if (line.startsWith("Tile")) {
                List<String> ls = new ArrayList<>();
                for (int in = i + 1; in <= i + 10; in++) {
                    ls.add(lines.get(in));
                }
                tiles.add(new Tile(line, ls));
            }
        }
    }

    void part_1() {
        for (Tile tile : tiles) {
            for (Tile with : tiles) {
                tile.fitsTo(with);
            }
        }
        List<Long> ids = tiles.stream()
                .filter(Tile::isCorner)
                .map(Tile::getId)
                .collect(Collectors.toList());

        long prod = 1;
        for (Long id : ids) {
            prod *= id;
        }

        System.out.println(prod);
    }
}
