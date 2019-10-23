package com.me.interview.constants;

import java.time.format.DateTimeFormatter;

public final class CommonConstants {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
    public static final String PAYMENT = "PAYMENT";
    public static final String REVERSAL = "REVERSAL";
}
