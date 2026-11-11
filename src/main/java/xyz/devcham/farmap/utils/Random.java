package xyz.devcham.farmap.utils;

import java.security.SecureRandom;
import java.util.HexFormat;

public final class Random {
    private static final SecureRandom RANDOM = new SecureRandom();

    static public String generateId(int length) {
        if (length <= 0)
            throw new IllegalArgumentException(
                    "the length of a random ID should be greater than 0");
        byte[] bytes = new byte[(length + 1) / 2];
        RANDOM.nextBytes(bytes);
        return HexFormat.of().formatHex(bytes).substring(0, length);
    }
}
