package com.berktas.rentacar.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeStampUtility {
    public static String timeStampString(){
        return String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    }
    public static String timeStampMSecondsString(){
        return new Timestamp(System.currentTimeMillis()).getTime()+"";
    }
}
