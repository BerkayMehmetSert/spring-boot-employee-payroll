package com.bms.employeepayroll.core.utilities.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateHelper {
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
