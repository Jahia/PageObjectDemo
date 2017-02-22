package core.utils;

import java.util.Random;

/**
 * Created by Sergey Mokhov (sergey) on 2017-02-22.
 */
public final class RandomWordGenerator {
    /**
     * This method generates a random word for a given length.
     *
     * @param length Desired word length
     * @return String. word.
     */
    public static String generate(int length) {
        Random random = new Random();
        StringBuilder word = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            word.append((char) ('a' + random.nextInt(26)));
        }
        return word.toString();
    }
}
