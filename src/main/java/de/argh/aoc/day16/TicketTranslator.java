package de.argh.aoc.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class TicketTranslator {

    final List<Field> fields = new ArrayList<>();
    final Ticket myTicket;
    final List<Ticket> nearby = new ArrayList<>();

    TicketTranslator(List<String> lines) {
        int ti = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("")) {
                ti = i + 2;
                break;
            }
            fields.add(new Field(lines.get(i)));
        }

        myTicket = new Ticket(lines.get(ti));
        for (int i = ti + 3; i < lines.size(); i++) {
            nearby.add(new Ticket(lines.get(i)));
        }
    }

    void part_1() {
        System.out.println(nearby.stream()
                .mapToInt(n -> n.getValuesNotApplyingRules(fields)
                        .stream()
                        .mapToInt(i -> i)
                        .sum())
                .sum());
    }

    void part_2() {
        List<Ticket> allValid = getAllValidTickets();
        Map<Integer, List<Field>> mapping = initMapping(allValid);

        reduceMapping(mapping);
        System.out.println(getProduct(mapping));
    }

    /**
     * Collecting all valid Tickets.
     */
    private List<Ticket> getAllValidTickets() {
        List<Ticket> allValid = new ArrayList<>(nearby);
        allValid.removeIf(n -> !n.getValuesNotApplyingRules(fields)
                .isEmpty());
        allValid.add(myTicket);
        return allValid;
    }

    /**
     * Here all rows in a ticket will be itereated. The values of all Tickets in one row will be checked with the set
     * of rules by a field. Every field matching the row will be collected on the actual index.
     *
     * @param allValid all valid Tickets
     * @return Map of index with matching fields.
     */
    private Map<Integer, List<Field>> initMapping(List<Ticket> allValid) {
        Map<Integer, List<Field>> mapping = new HashMap<>();
        for (int i = 0; i < myTicket.values.size(); i++) {
            int index = i;
            List<Field> found = fields.stream()
                    .filter(r -> allValid.stream()
                            .map(t -> t.get(index))
                            .allMatch(r::contains))
                    .collect(Collectors.toList());
            mapping.put(index, found);
        }
        return mapping;
    }

    /**
     * Since when one rule is applying to just one index, it has to be removed from all other indexes. this will be
     * continued until every index hast just one field left.
     *
     * @param mapping Indexes with matching fields.
     */
    private void reduceMapping(Map<Integer, List<Field>> mapping) {
        while (!mapping.values()
                .stream()
                .allMatch(v -> v.size() == 1)) {
            mapping.values()
                    .stream()
                    // finding the one
                    .filter(v -> v.size() == 1)
                    // then remove from the others
                    .forEach(v -> {
                        mapping.values()
                                .stream()
                                // others have more than one
                                .filter(r -> r.size() > 1)
                                .forEach(r -> r.remove(v.get(0)));
                    });
        }
    }

    /**
     * Getting all fields starting with departure and multiply the values ob my ticket.
     *
     * @param mapping reduced mapping of indexes and matching fields.
     */
    private long getProduct(Map<Integer, List<Field>> mapping) {
        long product = 1;
        for (Map.Entry<Integer, List<Field>> entry : mapping.entrySet()) {
            Field field = entry.getValue()
                    .get(0);
            if (field.startsWith("departure ")) {
                System.out.println(field.name + " : " + myTicket.get(entry.getKey()));
                product = product * myTicket.get(entry.getKey());
            }
        }
        return product;
    }

}
