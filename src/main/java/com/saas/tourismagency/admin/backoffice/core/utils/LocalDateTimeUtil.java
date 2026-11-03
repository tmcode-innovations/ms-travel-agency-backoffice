package com.saas.tourismagency.admin.backoffice.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeUtil {

    public interface DATE_FORMAT {
       String DATE_SHORT = "dd/MM/yyyy";
       String DATE_SHORT_OTHER_FORMAT = "yyyy-MM-dd";
       String DATE_LONG = "EEEE, dd 'de' MMMM 'de' yyyy";
       String TIME_SHORT = "HH:mm:ss";
       String TIME_VERY_SHORT = "HH:mm";
       String TIME_LONG = "h:mm a";
       String DATE_TIME_LONG = "EEEE, dd/MM/yyyy HH:mm";
       String DATE_TIME = "dd/MM/yyyy HH:mm:ss";
       String DATE_TIME_OTHER_SHORT_TIME = "dd/MM/yyyy HH:mm";
       String DATE_TIME_OTHER_SHORT_TIME_1 = "dd/MM/yyyy";
       String DATE_TIME_OTHER = "yyyy-MM-dd HH:mm:ss";
       String DATE_TIME_OTHER_SHORT = "MMM dd";
       String DATE_TIME_OTHER_SHORT_ONLY_DAY = "dd";
       String DATE_TIME_OTHER_SHORT_ONLY_MOUNTH = "MMM";
    }

    public static String toString(LocalDateTime localDateTime, String format){
        if(localDateTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDateTime);
    }

    public static String toString(LocalDate localDate, String format){
        if(localDate == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDate);
    }

    public static String toLocalDateFormatShort(LocalDate localDate){
        return toString(localDate, DATE_FORMAT.DATE_SHORT);
    }

    public static String toLocalDateTimeFormatShort(LocalDateTime localDateTime){
        return toString(localDateTime, DATE_FORMAT.DATE_SHORT);
    }

    public static String toLocalDateTimeFormatDateTime(LocalDateTime localDateTime){
        return toString(localDateTime, DATE_FORMAT.DATE_TIME);
    }

    public static String toLocalDateFormatShortOther(LocalDate localDate){
        return toString(localDate, DATE_FORMAT.DATE_SHORT_OTHER_FORMAT);
    }

    public static String toLocalDateTimeFormatDateTimeShort(LocalDateTime localDateTime){
        return toString(localDateTime, DATE_FORMAT.TIME_VERY_SHORT);
    }

    public static String toLocalDateTimeFormatDateTimeLong(LocalDateTime localDateTime){
        return toString(localDateTime, DATE_FORMAT.DATE_TIME_LONG);
    }

    public static String toLocalDateTimeFormatDateTimeOtherShort(LocalDateTime localDateTime){
        return toString(localDateTime, DATE_FORMAT.DATE_TIME_OTHER_SHORT_TIME);
    }

    public static String toLocalDateTimeFormatDateTimeOtherShort_1(LocalDateTime localDateTime){
        return toString(localDateTime, DATE_FORMAT.DATE_TIME_OTHER_SHORT_TIME_1);
    }

    public static String toLocalDateTimeFormatDateTimeOtherShortOnlyMonthAndDay(LocalDateTime localDateTime){
        return toString(localDateTime, DATE_FORMAT.DATE_TIME_OTHER_SHORT);
    }

    public static String toShortDateFromFilter(String value){
        if (value == null || value.isBlank()) return value;
        String v = value.trim();
        try {
            return toString(OffsetDateTime.parse(v).toLocalDate(), DATE_FORMAT.DATE_SHORT);
        } catch (DateTimeParseException ignored) {
        }
        try {
            return toString(LocalDateTime.parse(v).toLocalDate(), DATE_FORMAT.DATE_SHORT);
        } catch (DateTimeParseException ignored) {
        }
        try {
            return toString(LocalDate.parse(v), DATE_FORMAT.DATE_SHORT);
        } catch (DateTimeParseException ignored) {
        }
        return value;
    }
}
