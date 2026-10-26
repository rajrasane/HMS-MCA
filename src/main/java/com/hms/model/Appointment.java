package com.hms.model;
import java.time.*;
public record Appointment(int id, int patientId, String patientName, int doctorId, String doctorName,
                          LocalDate date, LocalTime startTime, LocalTime endTime, String status, String notes) {}
