package com.saas.tourismagency.admin.backoffice.core.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.math.RoundingMode;

public class NumberUtil {

    public interface NUMBER_FORMAT {
        String DEFAULT = "#,##0.00";
        String DEFAULT_THREE_DECIMAL = "#,##0.000";
    }

    public static String toDefaultFormat(){
        return NUMBER_FORMAT.DEFAULT;
    }

    public static String toDefaultFormatThreeDecimal(){
        return NUMBER_FORMAT.DEFAULT_THREE_DECIMAL;
    }

    public static Locale toLocaleEnglish(){
        return Locale.ENGLISH;
    }

    public static String getImportFormat(Double value) {
        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance(NumberUtil.toLocaleEnglish());
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        decimalFormat.applyPattern(NumberUtil.toDefaultFormat());
        String format = null;
        if ( value != null ){
            format = decimalFormat.format(value);
        }
        return format;
    }

    public static String getImportFormatThreeDecimal(Double value) {
        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance(NumberUtil.toLocaleEnglish());
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        decimalFormat.applyPattern(NumberUtil.toDefaultFormatThreeDecimal());
        String format = null;
        if ( value != null ){
            format = decimalFormat.format(value);
        }
        return format;
    }

}
