package com.saas.tourismagency.admin.backoffice.core.utils;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class NameNormalizer {

    private NameNormalizer() {
    }

    /** Canonico para almacenar: trim + colapsar espacios internos + MAYUSCULAS (conserva acentos). */
    public static String normalize(String value) {
        if (value == null) {
            return null;
        }
        return value.trim().replaceAll("\\s+", " ").toUpperCase(Locale.ROOT);
    }

    /** Regex que matchea variantes de mayus/minus y espacios sobrantes sobre el valor almacenado. */
    public static String exactLikePattern(String value) {
        if (value == null) {
            return "^$";
        }
        String[] tokens = value.trim().split("\\s+");
        if (tokens.length == 0 || (tokens.length == 1 && tokens[0].isEmpty())) {
            return "^$";
        }
        return "^\\s*" + Arrays.stream(tokens)
                .map(Pattern::quote)
                .collect(Collectors.joining("\\s+")) + "\\s*$";
    }
}
