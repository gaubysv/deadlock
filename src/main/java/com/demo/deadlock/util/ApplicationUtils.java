package com.demo.deadlock.util;

import java.util.concurrent.TimeUnit;

public final class ApplicationUtils {

    public static void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (Exception e) {
            // Do nothing.
        }
    }

    private ApplicationUtils() {
        // NO-OP
    }
}
