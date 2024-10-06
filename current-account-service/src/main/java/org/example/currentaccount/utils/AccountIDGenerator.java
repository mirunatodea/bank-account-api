package org.example.currentaccount.utils;

import java.util.concurrent.atomic.AtomicLong;

public class AccountIDGenerator {
    private static final AtomicLong counter = new AtomicLong(0);

    public static Long generateUniqueId() {
        return counter.incrementAndGet();
    }
}
