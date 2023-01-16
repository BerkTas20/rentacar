package com.berktas.rentacar.core.util.tracing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MetreToKm {

    public static double convert(long metre, int scale) {
        BigDecimal bigDecimal = BigDecimal.valueOf(metre / 1000D);
        bigDecimal = bigDecimal.setScale(scale, RoundingMode.HALF_UP);

        return bigDecimal.doubleValue();
    }

    public static double convert(long metre){
        return convert(metre,3);
    }

}
