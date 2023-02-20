package com.profilespage.profilespagebe.utils;

import com.profilespage.profilespagebe.exception.NotFoundException;

public class Assert {

    private Assert() {
        throw new UnsupportedOperationException("Assert class cannot be instantiated through the constructor. It's methods should be references statically");
    }

    public static <T> T notNull(T object, String message) {
        if (object == null) {
            throw new NotFoundException(message);
        }
        return object;
    }

    public static String notBlank(String string, String message) {
        if (string.length() == 0) {
            throw new NotFoundException(message);
        }
        return string;
    }
}
