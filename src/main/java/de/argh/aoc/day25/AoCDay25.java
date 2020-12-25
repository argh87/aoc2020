package de.argh.aoc.day25;

public class AoCDay25 {

    final static long card_pkey = 15335876L;
    final static long door_pkey = 15086442L;

    final static long divider = 20201227L;

    public static void main(String[] args) {
        final long cardLoopSize = getLoopSize(card_pkey);
        final long doorLoopSize = getLoopSize(door_pkey);

        System.out.println(getEncryptionKey(card_pkey, doorLoopSize));
        System.out.println(getEncryptionKey(door_pkey, cardLoopSize));
    }

    static long getLoopSize(long pkey) {
        long loops = 0L;
        long value = 1L;
        while (value != pkey) {
            value = (value * 7) % divider;
            loops++;
        }
        return loops;
    }

    static long getEncryptionKey(long pkey, long loops) {
        long value = 1;
        for (long i = 0; i < loops; i++) {
            value = (value * pkey) % divider;

        }
        return value;
    }
}
