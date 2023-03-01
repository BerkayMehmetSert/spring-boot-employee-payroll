package com.bms.employeepayroll.core.utilities.services.rules;

public class ValidationRules {
    public static void run(Runnable... rules) {
        for (var rule : rules) {
            rule.run();
        }
    }
}
