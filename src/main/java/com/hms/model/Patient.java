package com.hms.model;
import java.time.LocalDate;
public record Patient(int id, String name, LocalDate dateOfBirth, String gender, String bloodGroup,
                      String phone, String email, String address, String emergencyContact) {
    public int age() { return dateOfBirth == null ? 0 : java.time.Period.between(dateOfBirth, LocalDate.now()).getYears(); }
}
