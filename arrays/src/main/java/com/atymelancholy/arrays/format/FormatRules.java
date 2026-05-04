package com.atymelancholy.arrays.format;

import java.util.regex.Pattern;

public final class FormatRules {

    public static final Pattern INTEGER_TOKEN = Pattern.compile("^[-+]?\\d+$");
    public static final Pattern ELEMENT_SEPARATOR = Pattern.compile("[,;\\s\\u2013\\u2014\\-]+");

    private FormatRules() {
    }
}
