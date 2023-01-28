package hu.pizzaorder.backend.util;

import java.util.UUID;

public class TokenFactory {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
