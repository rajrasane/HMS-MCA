package com.hms.util;
import java.time.*;
import java.util.regex.Pattern;
public final class Validation {
    private Validation() {}
    public static String required(String value, String label) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException(label + " is required.");
        return value.trim();
    }
    public static int positiveInt(String value, String label) {
        try { int n=Integer.parseInt(value); if(n<=0) throw new Exception(); return n; }
        catch(Exception e){throw new IllegalArgumentException(label+" must be a positive number.");}
    }
    public static LocalDate date(String value, String label) {
        try { return LocalDate.parse(required(value,label)); } catch(Exception e){throw new IllegalArgumentException(label+" must be YYYY-MM-DD.");}
    }
    public static LocalTime time(String value, String label) {
        try { return LocalTime.parse(required(value,label)); } catch(Exception e){throw new IllegalArgumentException(label+" must be HH:MM.");}
    }
    public static void email(String value) { if(value != null && !value.isBlank() && !Pattern.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$",value)) throw new IllegalArgumentException("Email is invalid."); }
    public static void phone(String value) {
        if (value == null || !Pattern.matches("^\\+?[0-9][0-9 ()-]{6,19}$", value.trim()))
            throw new IllegalArgumentException("Phone must contain 7-20 digits and may include +, spaces, brackets, or hyphens.");
    }
}
