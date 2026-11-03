package com.saas.tourismagency.admin.backoffice.core.utils;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;


@Service
public class DateUtil {
    private DateTimeFormatter formatterInstant = DateTimeFormatter.ISO_INSTANT;


    public String transformAnotherFormat(String valueDate,String format){
        //yyyy-MM-dd
        LocalDate localDate = LocalDate.parse(valueDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }

    public String transformAnotherFormat(String valueDate,String formatOrigin,String format){
        DateTimeFormatter formatterOrigin = DateTimeFormatter.ofPattern(formatOrigin);
        LocalDate localDate = LocalDate.parse(valueDate,formatterOrigin);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);

    }

    public LocalDate toLocalDate(String dateString,String format){
        if(dateString == null || dateString.trim().isEmpty() || dateString.trim().isBlank()){
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateString,formatter);
    }

    //ESTE EVITA QUE SE CAMBIE LA FECHA POR LA ZONA HORARIA
    public String toString(Instant instant,String format){
        if(instant == null) return "";

        String instantString = instant.toString().replace("Z","");
        int dotIndex = instantString.indexOf(".");
        String dateString = dotIndex != -1 ? instantString.substring(0, dotIndex) : instantString;
        dateString = dateString.replace("T", " ");
        LocalDateTime localDateTime = toLocalDateTime(dateString,"yyyy-MM-dd HH:mm:ss");
        return toString(localDateTime,format);
    }

    public String toString(LocalDateTime localDateTime,String format){
        if(localDateTime == null) return "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDateTime);
    }

    public String toString(LocalDate localDate,String format){
        if(localDate == null) return "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDate);
    }

    public String toString(Date date,String format){
        if(date == null) return "";
        LocalDate localDate = toLocalDate(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDate);
    }

    public LocalDate toLocalDate(Instant dateInstant,boolean withoutTimeZone){
        if (dateInstant == null)
            return  null;
        if(withoutTimeZone){
            String formatDate = "yyyy-MM-dd";

            String dateString = toString(dateInstant,"yyyy-MM-dd");
            return toLocalDate(dateString,formatDate);
        }
        return dateInstant.atZone(ZoneOffset.UTC).toLocalDate();
    }

    public LocalDate toLocalDate(Instant dateInstant,String zoneTime){
        if (dateInstant == null) {
            return  null;
        }
        return dateInstant.atZone(ZoneId.of(zoneTime)).toLocalDate();
    }

    public LocalDate toLocalDate(Date date){
        if (date == null) {
            return  null;
        }
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneOffset.UTC)
                .toLocalDate();
    }

    public LocalDateTime toLocalDateTime(LocalDate localDate){
        if (localDate == null) {
            return  null;
        }
        return localDate.atStartOfDay();
    }

    public LocalDateTime toLocalDateTime(Instant dateInstant){
        if (dateInstant == null) {
            return  null;
        }
        return dateInstant.atZone(ZoneOffset.UTC).toLocalDateTime();
    }

    public LocalDateTime toLocalDateTime(Instant dateInstant,String zoneTime){
        if (dateInstant == null) {
            return  null;
        }
        return dateInstant.atZone(ZoneId.of(zoneTime)).toLocalDateTime();
    }

    public LocalDateTime toLocalDateTime(String date,String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        // Transformar la cadena en un LocalDateTime
        return LocalDateTime.parse(date, formatter);
    }

    public LocalDateTime toLocalDateTime(Date date){
        if (date == null) {
            return  null;
        }
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime();
    }

    public Instant toInstant(String instantString){
        if(instantString  == null || instantString.isEmpty() || instantString.isBlank())
            return null;
        return Instant.from(formatterInstant.parse(instantString));
    }

    public Instant toInstant(LocalDateTime date){
        if(date == null )return null;

        return date.atZone(ZoneId.of("UTC")).toInstant();
    }

    public Instant toInstant(LocalDate date){
        if(date == null )return null;

        return date.atStartOfDay()
                .atZone(ZoneId.of("UTC"))
                .toInstant();
    }

    public Date toDate(LocalDate localDate){
        if(localDate == null) return null;
        String dateString = toString(localDate,"yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dates = null;
        try {
            dates = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }

    public Date toDate(LocalDateTime localDateTime){
        if(localDateTime == null) return null;
        String dateString = toString(localDateTime,"yyyy-MM-dd HH:mm:ss a");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        Date dates = null;
        try {
            dates = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }

    public LocalDateTime toStartOfDay(LocalDate date) {
        return Optional.ofNullable(date).map(LocalDate::atStartOfDay).orElse(null);
    }

    public LocalDateTime toEndOfDay(LocalDate date) {
        return Optional.ofNullable(date).map((localDate) ->
                localDate.atTime(LocalTime.MAX)).orElse(null);
    }

    public LocalDateTime formatIso8601ToLocalDateTime(String dateIso8601) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime dateTime = LocalDateTime.parse(dateIso8601, formatter);
        return dateTime;
    }

    public LocalDate formatIso8601ToLocalDate(String dateIso8601) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDate date = LocalDate.parse(dateIso8601, formatter);
        return date;
    }

    public LocalDate formatSimpleDateToLocalDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            try {
                return OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDate();
            } catch (DateTimeParseException e2) {
                return LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toLocalDate();
            }
        }
    }

    public LocalDateTime formatIso8601ToLocalDateTimeInit(String dateIso8601) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime dateTime = LocalDateTime.parse(dateIso8601, formatter);
        LocalDateTime result = LocalDateTime.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth(), 0, 0);
        return result;
    }

    public LocalDateTime formatIso8601ToLocalDateTimeEnd(String dateIso8601) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime dateTime = LocalDateTime.parse(dateIso8601, formatter);
        LocalDateTime result = LocalDateTime.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth(), 23, 59);
        return result;
    }
}
