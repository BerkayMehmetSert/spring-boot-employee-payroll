package com.bms.employeepayroll.core.utilities.services.rules;

import java.util.function.Supplier;

public class BusinessRules {
    public static void run(Runnable... rules) {
        for (var rule : rules) {
            rule.run();
        }
    }

    public static <T> T run(Supplier<T>... rules) {
        for (var rule : rules) {
            return rule.get();
        }
        return null;
    }
}
